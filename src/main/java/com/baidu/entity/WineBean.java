package com.baidu.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_wine")
@Data
public class WineBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wid;
    private String cardno;
    private String wname;
    private String wtype;
    private String madedate;
    private String address;
    private Double wprice;
    private String vol;

    public WineBean() {
    }

    public WineBean(String cardno, String wname, String wtype, String madedate, String address, Double wprice, String vol) {
        this.cardno = cardno;
        this.wname = wname;
        this.wtype = wtype;
        this.madedate = madedate;
        this.address = address;
        this.wprice = wprice;
        this.vol = vol;
    }
}
