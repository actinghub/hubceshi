package com.baidu.service;

import com.baidu.entity.SmokeBean;
import com.baidu.entity.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> findAll();

    UserBean getStr1Info(String str1);

    String getDataInterfaceInfo(String str1, String str2);

    List<SmokeBean> findOne(String cardno);

    String receDataInterface(String str1, String str2);
}
