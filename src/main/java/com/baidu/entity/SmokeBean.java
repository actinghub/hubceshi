package com.baidu.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tb_smoke")
@Entity
public class SmokeBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    //这个本身就是唯一的数据，能不能作为主键
    //从数据库的角度没有任何问题
    //违背数据的设计原则，程序原则
    //主键不能含有任何业务功能
    private String cardno;
    private String sname;
    private String madedate;
    private String address;
    private Double sprice;

    public SmokeBean(String cardno, String sname, String madedate, String address, Double sprice) {
        this.cardno = cardno;
        this.sname = sname;
        this.madedate = madedate;
        this.address = address;
        this.sprice = sprice;
    }

    public SmokeBean() {
    }
}
