package com.baidu.utils;

import com.baidu.entity.SmokeBean;
import com.baidu.entity.WineBean;

public class SplicingXml {
    public static String SplicingRsXmlError(String code){
        return  "<result>" +
                "   <MEG>" +
                "       <CODE>"+code+"</CODE>" +
                "   </MEG>" +
                "</result>";
    }

    public static String SplicingRsXmlSmoke(SmokeBean smokeBean) {
        return  "<result>" +
                "   <MEG>" +
                "       <CODE>3</CODE>" +
                "   </MEG>" +
                "   <CONTENT>" +
                "       <CARDNO>"+smokeBean.getCardno()+"</CARDNO>" +
                "       <SNAME>"+smokeBean.getSname()+"</SNAME>" +
                "       <MADEDATE>"+smokeBean.getMadedate()+"</MADEDATE>" +
                "       <ADDRESS>"+smokeBean.getAddress()+"</ADDRESS>" +
                "       <SPRICE>"+smokeBean.getSprice()+"</SPRICE>" +
                "   </CONTENT>" +
                "</result>";
    }

    public static String SplicingRsXmlWine(WineBean wineBean) {
        return  "<result>" +
                "   <MEG>" +
                "       <CODE>3</CODE>" +
                "   </MEG>" +
                "   <CONTENT>" +
                "       <CARDNO>"+wineBean.getCardno()+"</CARDNO>" +
                "       <WNAME>"+wineBean.getWname()+"</WNAME>" +
                "       <WTYPE>"+wineBean.getWtype()+"</WTYPE>" +
                "       <MADEDATE>"+wineBean.getMadedate()+"</MADEDATE>" +
                "       <ADDRESS>"+wineBean.getAddress()+"</ADDRESS>" +
                "       <WPRICE>"+wineBean.getWprice()+"</WPRICE>" +
                "       <VOL>"+wineBean.getVol()+"</VOL>" +
                "   </CONTENT>" +
                "</result>";
    }

    public static String splicingRsXmlAll(Integer code, String content) {
        return "<MEG><CODE>"+code+"</CODE><CONTENT>"+content+"</CONTENT></MEG>";
    }
}
