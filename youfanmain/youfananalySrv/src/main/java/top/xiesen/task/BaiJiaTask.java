package top.xiesen.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.utils.ParameterTool;
import top.xiesen.entity.BaiJiaInfo;
import top.xiesen.map.BaiJiaMap;
import top.xiesen.reduce.BaiJiaReduce;
import top.xiesen.utils.DateUtils;
import top.xiesen.utils.HbaseUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 败家指数计算 task
 * @Author 谢森
 * @Date 2019/8/5 15:03
 */
public class BaiJiaTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);

        DataSource<String> text = env.readTextFile(params.get("input"));
        DataSet<BaiJiaInfo> mapResult = text.map(new BaiJiaMap());
        DataSet<BaiJiaInfo> reduceResult = mapResult.groupBy("groupField").reduce(new BaiJiaReduce());

        try {
            List<BaiJiaInfo> baiJiaInfoList = reduceResult.collect();
            for (BaiJiaInfo baiJiaInfo : baiJiaInfoList) {
                String userId = baiJiaInfo.getUserId();
                List<BaiJiaInfo> list = baiJiaInfo.getList();
                // list 集合排序
                Collections.sort(list, new Comparator<BaiJiaInfo>() {
                    @Override
                    public int compare(BaiJiaInfo o1, BaiJiaInfo o2) {
                        String createTime1 = o1.getCreateTime();
                        String createTime2 = o2.getCreateTime();
                        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
                        Date dateNow = new Date();
                        Date time1 = dateNow;
                        Date time2 = dateNow;
                        try {
                            time1 = dataFormat.parse(createTime1);
                            time2 = dataFormat.parse(createTime2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return time1.compareTo(time2);
                    }
                });

                Map<Integer, Integer> frequencyMap = new HashMap<>();
                double maxAmount = 0d;
                BaiJiaInfo before = null;
                double sum = 0d;
                for (BaiJiaInfo baiJiaInfoInner : list) {
                    if (before == null) {
                        before = baiJiaInfoInner;
                        continue;
                    }
                    // 计算购买频率
                    String beforeTime = before.getCreateTime();
                    String endTime = baiJiaInfoInner.getCreateTime();
                    int days = DateUtils.getBetweenByStartAndEnd(beforeTime, endTime, "yyyyMMdd hhmmss");
                    int brefore = frequencyMap.get(days) == null ? 0 : frequencyMap.get(days);
                    frequencyMap.put(days, brefore + 1);

                    // 计算最大金额
                    String totalAmountStr = baiJiaInfoInner.getTotalAmount();
                    Double totalAmount = Double.valueOf(totalAmountStr);
                    if (totalAmount > maxAmount) {
                        maxAmount = totalAmount;
                    }

                    // 计算平均值
                    sum += totalAmount;
                    before = baiJiaInfoInner;
                }

                double avgAmount = sum / list.size();
                int totalDays = 0;
                Set<Map.Entry<Integer, Integer>> set = frequencyMap.entrySet();
                for (Map.Entry<Integer, Integer> entry : set) {
                    Integer frequencyDays = entry.getKey();
                    Integer count = entry.getValue();
                    totalDays += frequencyDays * count;
                }

                // 平均天数(下单频率)
                int avgDays = totalDays / list.size();
                
                /**
                 * 败家指数 = 支付金额平均值 * 0.3 、最大支付金额 * 0.3、下单频率 *0.4
                 * 支付金额平均值 30分(0-20 5; 20-60 10; 60-100 20; 100-150 30; 150-200 40; 200-250 60; 250-350 70; 350-450 80; 450-600 90; 600以上 100)
                 * 最大支付金额 30分(0-20 5; 20-60 10; 60-200 30; 200-500 60; 500-700 80; 700以上 100)
                 * 下单频率 40分(0-5 100; 5-10 90; 10-30 70; 30-60 60; 60-80 40; 80-100 20; 100以上的 10)
                 */
                int avgAmountScore = 0;
                if (avgAmount >= 0 && avgAmount < 20) {
                    avgAmountScore = 5;
                } else if (avgAmount >= 20 && avgAmount < 60) {
                    avgAmountScore = 10;
                } else if (avgAmount >= 60 && avgAmount < 100) {
                    avgAmountScore = 20;
                } else if (avgAmount >= 100 && avgAmount < 150) {
                    avgAmountScore = 30;
                } else if (avgAmount >= 150 && avgAmount < 200) {
                    avgAmountScore = 40;
                } else if (avgAmount >= 200 && avgAmount < 250) {
                    avgAmountScore = 60;
                } else if (avgAmount >= 250 && avgAmount < 350) {
                    avgAmountScore = 70;
                } else if (avgAmount >= 350 && avgAmount < 450) {
                    avgAmountScore = 80;
                } else if (avgAmount >= 450 && avgAmount < 600) {
                    avgAmountScore = 90;
                } else if (avgAmount >= 600) {
                    avgAmountScore = 100;
                }

                // 0-20 5; 20-60 10; 60-200 30; 200-500 60; 500-700 80; 700以上 100
                int maxAmountScore = 0;
                if (maxAmount >= 0 && maxAmount < 20) {
                    maxAmountScore = 5;
                } else if (maxAmount >= 20 && maxAmount < 60) {
                    maxAmountScore = 10;
                } else if (maxAmount >= 60 && maxAmount < 200) {
                    maxAmountScore = 30;
                } else if (maxAmount >= 200 && maxAmount < 500) {
                    maxAmountScore = 60;
                } else if (maxAmount >= 500 && maxAmount < 700) {
                    maxAmountScore = 80;
                } else if (maxAmount >= 700) {
                    maxAmountScore = 100;
                }

                // 0-5 100; 5-10 90; 10-30 70; 30-60 60; 60-80 40; 80-100 20; 100以上的 10
                int avgDaysScore = 0;
                if (avgDays >= 0 && avgDays < 5) {
                    avgDaysScore = 100;
                } else if (avgDays >= 5 && avgDays < 10) {
                    avgDaysScore = 90;
                } else if (avgDays >= 10 && avgDays < 30) {
                    avgDaysScore = 70;
                } else if (avgDays >= 30 && avgDays < 60) {
                    avgDaysScore = 60;
                } else if (avgDays >= 60 && avgDays < 80) {
                    avgDaysScore = 40;
                } else if (avgDays >= 80 && avgDays < 100) {
                    avgDaysScore = 20;
                } else if (avgDays >= 100) {
                    avgDaysScore = 10;
                }

                double totalScore = (avgAmountScore / 100) * 30 + (maxAmountScore / 100) * 30 + (avgDaysScore / 100) * 40;
                String tableName = "userflaginfo";
                String rowkey = userId;
                String familyName = "baseinfo";
                String colum = "baijiascore"; // 败家指数
                HbaseUtils.putdata(tableName, rowkey, familyName, colum, totalScore + "");

            }
            env.execute("BaiJiaTask");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
