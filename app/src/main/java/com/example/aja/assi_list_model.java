package com.example.aja;

public class assi_list_model {
    public assi_list_model(String assi_id, String name, String subj, String des,String url) {

        this.assi_id=assi_id;
        this.name=name;
        this.subj=subj;
        this.des=des;
        this.url=url;
    }

    public assi_list_model(){}

    String assi_id,name,subj,des,url;

    public String getAssi_id() {
        return assi_id;
    }

    public void setAssi_id(String assi_id) {
        this.assi_id = assi_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getSubj() {
        return subj;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        this.url = url;
    }
}


