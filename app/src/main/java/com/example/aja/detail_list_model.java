package com.example.aja;

public class detail_list_model {
    public detail_list_model(String detail_id, String name, String reg, String mob, String mail, String gen, String year) {

        this.detail_id=detail_id;
        this.name=name;
        this.reg=reg;
        this.mob=mob;
        this.mail=mail;
        this.year=year;
        this.gen=gen;
    }
    public detail_list_model(){}

    String detail_id,name,reg,mob,mail,year,gen;

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String lea_id) {
        this.detail_id = detail_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
