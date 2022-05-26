package com.baidu.utils;

import com.baidu.entity.SmokeBean;
import com.baidu.entity.UserBean;
import com.baidu.entity.WineBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AnalysisXml {
    public static UserBean analysisStr1(String str1){
        /**
         * xml解析的接种方式我们使用的是dom4J
         */
        Document document = null;
        Element root = null;
        UserBean user = null;
        try {
            /**
             * 这个地方报异常，就是xml解析失败
             */
            document = DocumentHelper.parseText(str1);
            root = document.getRootElement();
            String uname = root.elementText("UNAME");
            String pwd = root.elementText("PWD");
            String code = root.elementText("CODE");
            user = new UserBean(uname,pwd,code);
            return user;
        } catch (DocumentException e) {
            return user;
        }
    }

    public static String analysisStr2(String str2) {
        /**
         * xml解析的接种方式我们使用的是dom4J
         */
        Document document = null;
        Element root = null;
        try {
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            return cardno;
        } catch (DocumentException e) {
            return null;
        }
    }

    public static SmokeBean analysisStr2Smoke(String str2) {
        SmokeBean smokeBean = null;
        Document document = null;
        Element root = null;
        /**
         *     private String sname;
         *     private String madedate;
         *     private String address;
         *     private Double sprice;
         */
        try {
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            String sname = root.elementText("SNAME");
            String madedate = root.elementText("MADEDATE");
            String address = root.elementText("ADDRESS");
            String spriceStr = root.elementText("SPRICE");
            Double sprice = Double.valueOf(spriceStr);
            smokeBean = new SmokeBean(cardno, sname, madedate, address, sprice);
            return smokeBean;
        } catch (DocumentException e) {}
        return smokeBean;
    }

    public static WineBean analysisStr2Wine(String str2) {
        WineBean wineBean = null;
        Document document = null;
        Element root = null;
        /**
         *     private String wname;
         *     private String wtype;
         *     private String madedate;
         *     private String address;
         *     private Double wprice;
         *     private String vol;
         */
        try {
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            String wname = root.elementText("WNAME");
            String wtype = root.elementText("WTYPE");
            String madedate = root.elementText("MADEDATE");
            String address = root.elementText("ADDRESS");
            String wpriceStr = root.elementText("WPRICE");
            Double wprice = Double.valueOf(wpriceStr);
            String vol = root.elementText("VOL");
            wineBean = new WineBean(cardno, wname, wtype, madedate, address, wprice, vol);
            return wineBean;
        } catch (DocumentException e) {}
        return wineBean;
    }
}
