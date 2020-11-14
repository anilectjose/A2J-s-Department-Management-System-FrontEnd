package com.example.aja;

public class subject_list_model {
    public subject_list_model(String subj_id, String subject, String mark) {

        this.sub_id = subj_id;
        this.subject = subject;
        this.mark = mark;
    }
    public subject_list_model(){}

    String sub_id,subject,mark;

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
