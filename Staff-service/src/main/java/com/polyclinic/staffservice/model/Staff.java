package com.polyclinic.staffservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    private int id;
    @Column(name ="emri")
    private String emri;
    @Column(name ="mbiemri")
    private String mbiemri;
    @Column(name ="email")
    private String email;
    @Column(name ="tel")
    private String tel;
    @Column(name ="lloji")
    private String lloji;
    @Column(name ="aktiv")
    private byte aktiv;

    public Staff(String emri, String mbiemri, String email, String tel, String lloji, byte aktiv) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.tel = tel;
        this.lloji = lloji;
        this.aktiv = aktiv;
    }

    public Staff(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLloji() {
        return lloji;
    }

    public void setLloji(String lloji) {
        this.lloji = lloji;
    }

    public byte getAktiv() {
        return aktiv;
    }

    public void setAktiv(byte aktiv) {
        this.aktiv = aktiv;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", emri='" + emri + '\'' +
                ", mbiemri='" + mbiemri + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", lloji='" + lloji + '\'' +
                ", aktiv=" + aktiv +
                '}';
    }
}
