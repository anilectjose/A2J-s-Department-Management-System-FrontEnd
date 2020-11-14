package com.example.aja;

public class mark_list_model {
    public mark_list_model(String st_id, String name,String roleid) {

        this.stu_id=st_id;
        this.name=name;
        this.roleid=roleid;
    }
    public mark_list_model(){}

    String stu_id,name,roleid;
    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
