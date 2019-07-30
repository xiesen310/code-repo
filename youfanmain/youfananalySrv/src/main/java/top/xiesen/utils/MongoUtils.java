package top.xiesen.utils;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @Description mongodb 工具类
 * @Author 谢森
 * @Date 2019/7/29 16:10
 */
public class MongoUtils {
    private static MongoClient mongoClient = new MongoClient("192.168.231.147",27017);

    /**
     * mongodb 查询
     * @param tablename 表名
     * @param database 数据库名称
     * @param yearbasetype 年代标签
     * @return Document
     */
    public static Document findoneby(String tablename, String database, String yearbasetype){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection mongoCollection = mongoDatabase.getCollection(tablename);
        Document  doc = new Document();
        doc.put("yearbasetype", yearbasetype);
        FindIterable<Document> itrer = mongoCollection.find(doc);
        MongoCursor<Document> mongocursor = itrer.iterator();
        if(mongocursor.hasNext()){
            return mongocursor.next();
        }else{
            return null;
        }
    }


    /**
     * 保存更新 mongodb 数据
     * @param tablename 表名
     * @param database 数据库名称
     * @param doc 更新文档
     */
    public static void saveorupdatemongo(String tablename,String database,Document doc) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongocollection = mongoDatabase.getCollection(tablename);
        if(!doc.containsKey("_id")){
            ObjectId objectid = new ObjectId();
            doc.put("_id", objectid);
            mongocollection.insertOne(doc);
            return;
        }
        Document matchDocument = new Document();
        String objectid = doc.get("_id").toString();
        matchDocument.put("_id", new ObjectId(objectid));
        FindIterable<Document> findIterable =  mongocollection.find(matchDocument);
        if(findIterable.iterator().hasNext()){
            mongocollection.updateOne(matchDocument, new Document("$set",doc));
            try {
                System.out.println("come into saveorupdatemongo ---- update---"+ JSONObject.toJSONString(doc));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            mongocollection.insertOne(doc);
            try {
                System.out.println("come into saveorupdatemongo ---- insert---"+JSONObject.toJSONString(doc));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
