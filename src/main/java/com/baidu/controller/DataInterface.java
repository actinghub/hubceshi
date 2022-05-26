package com.baidu.controller;

import com.baidu.entity.SmokeBean;
import com.baidu.entity.UserBean;
import com.baidu.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/service")
public class DataInterface {
    @Resource
    private UserService userService;

    @RequestMapping("/getDataInterface")
    public String  getDataInterface(String str1,String str2){
        //str1 = "<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
       // str2 = "<CONTENT><CARDNO>xx123001</CARDNO></CONTENT>";
        String rsxml = userService.getDataInterfaceInfo(str1,str2);
        return rsxml;
    }

    @RequestMapping("/receDataInterface")
    public String receDataInterface(String str1,String str2){
        String rsxml = userService.receDataInterface(str1,str2);
        return rsxml;
    }

    @RequestMapping("/findOne")
    public List<SmokeBean> findOne(String cardno){
       return userService.findOne(cardno);
    }

    @GetMapping("/findAll")
    public List<UserBean> findAll(){
        return userService.findAll();
    }
}
