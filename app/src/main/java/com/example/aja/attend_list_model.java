package com.example.aja;

public class attend_list_model {
    public attend_list_model(String att_id, String name) {

        this.att_id=att_id;
        this.name=name;
    }
    public attend_list_model(){}

    String att_id,name;
    public String getAtt_id() {
        return att_id;
    }

    public void setAtt_id(String att_id) {
        this.att_id = att_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
