package com.nmy.spb.utils;

/**
 * @author nmy
 * @title: DatabasesTableNameTool
 * @date 2022-01-22 18:22
 */
public class DatabasesTableNameTool {

    public static String getAttentionTopicName(String name){
        return name + "attentiontopic";
    }

    public static String getFollowName(String name){
        return name + "follow";
    }

    public static String getFollowedName(String name){
        return name + "followed";
    }

    public static String getLikeName(String name){
        return name + "like";
    }
}
