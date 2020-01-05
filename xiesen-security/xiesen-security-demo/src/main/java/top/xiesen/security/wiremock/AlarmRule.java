package top.xiesen.security.wiremock;

import lombok.Data;

@Data
public class AlarmRule {
    private String id;
    private String parentId;
    private int ruleType;
    private String level;
    private String description;
    private String dataSubsetName;
    private String ruleExpression;
    private String alarmTitle;
    private String alarmContent;
    private String informType;
    private String calenderId;
    private String isAggregate;
}
