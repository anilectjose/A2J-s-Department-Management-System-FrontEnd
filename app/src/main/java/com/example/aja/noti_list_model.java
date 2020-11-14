package com.example.aja;

public class noti_list_model {

    public noti_list_model(String noti_id, String name, String slno) {

        this.noti_id=noti_id;
        this.name=name;
        this.slno=slno;

    }

    public noti_list_model(){}

    String noti_id,name,slno;


    public String getNoti_id() {
        return noti_id;
    }

    public void setNoti_id(String noti_id) {
        this.noti_id = noti_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }
}


