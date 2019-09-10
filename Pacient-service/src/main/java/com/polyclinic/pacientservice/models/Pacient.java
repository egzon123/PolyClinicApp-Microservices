package com.polyclinic.pacientservice.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "pacient")
public class Pacient {
    @Id
    private int id;

    @Column(name ="nr_identification")
    private String nrPersonal;
    @Column(name ="emri")
    private String emri;
    @Column(name ="mbiemri")
    private String mbiemri;
    @Column(name ="emri_prindit")
    private String emriPrindit;
    @Column(name ="gjinia")
    private char gjinia;
    @Column(name = "data_lindjes")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datalindjes;
    @Column(name ="adresa")
    private String adresa;
    @Column(name ="vendlindja")
    private String vendlindja;
    @Column(name ="tel")
    private String tel;
    @Column(name ="semundje_kronike")
    private String semundjeKronike;
    @Column(name ="alergji")
    private String alergji;


    public Pacient(String nrPersonal, String emri, String mbiemri, String emriPrindit, char gjinia, Date datalindjes, String adresa, String vendlindja, String tel, String semundjeKronike, String alergji) {
        this.nrPersonal = nrPersonal;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.emriPrindit = emriPrindit;
        this.gjinia = gjinia;
        this.datalindjes = datalindjes;
        this.adresa = adresa;
        this.vendlindja = vendlindja;
        this.tel = tel;
        this.semundjeKronike = semundjeKronike;
        this.alergji = alergji;
    }

    public Pacient(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNrPersonal() {
        return nrPersonal;
    }

    public void setNrPersonal(String nrPersonal) {
        this.nrPersonal = nrPersonal;
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

    public String getEmriPrindit() {
        return emriPrindit;
    }

    public void setEmriPrindit(String emriPrindit) {
        this.emriPrindit = emriPrindit;
    }

    public char getGjinia() {
        return gjinia;
    }

    public void setGjinia(char gjinia) {
        this.gjinia = gjinia;
    }

    public Date getDataLindjes() {
        return datalindjes;
    }

    public void setDataLindes(Date datalindjes) {
        this.datalindjes = datalindjes;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
//
    public String getVendlindja() {
        return vendlindja;
    }

    public void setVendlindja(String vendlindja) {
        this.vendlindja = vendlindja;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSemundjeKronike() {
        return semundjeKronike;
    }

    public void setSemundjeKronike(String semundjeKronike) {
        this.semundjeKronike = semundjeKronike;
    }

    public String getAlergji() {
        return alergji;
    }

    public void setAlergji(String alergji) {
        this.alergji = alergji;
    }

    @Override
    public String toString() {
        return id+"-"+emri+"-"+mbiemri+"-"+emriPrindit+"-"+gjinia+"-"+datalindjes+"-"+vendlindja+"-"+
                tel+"-"+adresa+"-"+semundjeKronike+"-"+alergji;
    }
}
