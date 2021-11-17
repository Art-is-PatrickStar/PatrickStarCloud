package com.wsw.patrickstar.enums;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/17 8:59
 * @Description: TODO
 */
public enum TopicEnum {
    TASK_TO_TASK("task_to_task", "task topic");

    public static final String TASK_TO_TASK_ID = "task_to_task_id";
    public static final String TASK_TO_TASK_TOPIC = "task_to_task";

    public final String topicId;
    public final String topicDesc;

    TopicEnum(String topicId, String topicDesc) {
        this.topicId = topicId;
        this.topicDesc = topicDesc;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public static TopicEnum getByTopicId(String topicId) {
        for (TopicEnum result : TopicEnum.values()) {
            if (result.getTopicId().equals(topicId)) {
                return result;
            }
        }
        return null;
    }
}
