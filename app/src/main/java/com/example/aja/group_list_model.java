package com.example.aja;

public class group_list_model {
    public group_list_model(String group_id, String group_name) {

        this.group_id=group_id;
        this.group_name=group_name;
    }

    public group_list_model(){}

    String group_id,group_name;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

}

