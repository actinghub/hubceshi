package com.baidu.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_user")
@Data
public class UserBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uname;
    private String pwd;

    @Transient
    private String code;

    public UserBean() {
    }

    public UserBean(String uname, String pwd, String code) {
        this.uname = uname;
        this.pwd = pwd;
        this.code = code;
    }
}
