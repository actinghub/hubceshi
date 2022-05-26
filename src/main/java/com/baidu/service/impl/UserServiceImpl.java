package com.baidu.service.impl;

import com.baidu.entity.SmokeBean;
import com.baidu.entity.UserBean;
import com.baidu.entity.WineBean;
import com.baidu.mapper.SmokeMapper;
import com.baidu.mapper.UserMapper;
import com.baidu.mapper.WineMapper;
import com.baidu.service.UserService;
import com.baidu.utils.AnalysisXml;
import com.baidu.utils.SplicingXml;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private WineMapper wineMapper;
    @Resource
    private SmokeMapper smokeMapper;

    @Override
    public List<UserBean> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public UserBean getStr1Info(String str1) {

        return AnalysisXml.analysisStr1(str1);
    }

    @Override
    public String getDataInterfaceInfo(String str1, String str2) {
        UserBean user = AnalysisXml.analysisStr1(str1);
        if(user==null){
            //str1解析失败
            return SplicingXml.SplicingRsXmlError("0");
        }else {
            //解析成功了，
            //登录
            if(StringUtils.isEmpty(user.getUname())){
                return SplicingXml.SplicingRsXmlError("0");
            }else {
                //用户名不为空,
                Example example = new Example(UserBean.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("uname", user.getUname());
                List<UserBean> list = userMapper.selectByExample(example);
                if(list!=null&&list.size()==1){
                    if(list.get(0).getPwd().equals(user.getPwd())){
                        //用户名和密码已经正确了。
                        //解析str2
                        String cardno = AnalysisXml.analysisStr2(str2);
                        if(StringUtils.isEmpty(cardno)){
                            return SplicingXml.SplicingRsXmlError("0");
                        }else {
                            /**
                             * 登录成功了，str2页解析成功，并且不为空
                             */
                            if(StringUtils.isEmpty(user.getCode())){
                                //str1里面的code为空
                                return SplicingXml.SplicingRsXmlError("0");
                            }else {
                                if("01".equals(user.getCode())){
                                    //香烟
                                    Example smokeexample = new Example(SmokeBean.class);
                                    Example.Criteria criteria1 = smokeexample.createCriteria();
                                    criteria1.andEqualTo("cardno", cardno);
                                    List<SmokeBean> slist = smokeMapper.selectByExample(smokeexample);
                                    if(slist!=null&&slist.size()==1){
                                        return SplicingXml.SplicingRsXmlSmoke(slist.get(0));
                                    }else {
                                        return SplicingXml.SplicingRsXmlError("2");
                                    }
                                }else if("02".equals(user.getCode())){
                                    //酒水
                                    Example wineexample = new Example(WineBean.class);
                                    wineexample.createCriteria().andEqualTo("cardno", cardno);
                                    List<WineBean> wlist = wineMapper.selectByExample(wineexample);
                                    if(wlist!=null&&wlist.size()==1){
                                        return SplicingXml.SplicingRsXmlWine(wlist.get(0));
                                    }else {
                                        return SplicingXml.SplicingRsXmlError("2");
                                    }
                                }else {
                                    return SplicingXml.SplicingRsXmlError("0");
                                }
                            }
                        }
                    }else {
                        return SplicingXml.SplicingRsXmlError("1");
                    }
                }else {
                    return SplicingXml.SplicingRsXmlError("1");
                }
            }
        }
    }

    @Override
    public List<SmokeBean> findOne(String cardno) {
        Example example = new Example(SmokeBean.class);
        Example.Criteria criteria = example.createCriteria();
       // criteria.andEqualTo("cardno", cardno);
        criteria.andLike("cardno", "%"+cardno+"%");
        List<SmokeBean> smokeBeans = smokeMapper.selectByExample(example);
        return smokeBeans;
    }

    @Override
    public String receDataInterface(String str1, String str2) {
        UserBean user = AnalysisXml.analysisStr1(str1);
        if(user==null){
            //str1解析失败
            return SplicingXml.splicingRsXmlAll(0,"参数str1解析失败");
        }else {
            //解析成功了，
            //登录
            if(StringUtils.isEmpty(user.getUname())){
                return SplicingXml.splicingRsXmlAll(0,"鉴权失败");
            }else {
                //用户名不为空,
                Example example = new Example(UserBean.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("uname", user.getUname());
                List<UserBean> list = userMapper.selectByExample(example);
                if(list!=null&&list.size()==1){
                    if(list.get(0).getPwd().equals(user.getPwd())){
                        //登录成功了,拿到code开始判断，01是香烟，02是酒水
                        String code = user.getCode();
                        if("01".equals(code)){
                            //代码需要大家完成
                            SmokeBean smokeBean = AnalysisXml.analysisStr2Smoke(str2);
                            if(smokeBean==null){
                                return SplicingXml.splicingRsXmlAll(0, "参数str2解析失败");
                            }else {
                                try {
                                    smokeMapper.insertSelective(smokeBean);
                                    return SplicingXml.splicingRsXmlAll(1, "保存成功");
                                }catch (Exception e){
                                    return SplicingXml.splicingRsXmlAll(0, "参数str2中数据保存失败");
                                }
                            }
                        }else if("02".equals(code)){
                            //代码没有完成
                            WineBean wineBean = AnalysisXml.analysisStr2Wine(str2);
                            if(wineBean==null){
                                return SplicingXml.splicingRsXmlAll(0, "参数str2解析失败");
                            }else {
                                try {
                                    wineMapper.insertSelective(wineBean);
                                    return SplicingXml.splicingRsXmlAll(1, "保存成功");
                                }catch (Exception e){
                                    return SplicingXml.splicingRsXmlAll(0, "参数str2中数据保存失败");
                                }
                            }
                        }else{
                            return SplicingXml.splicingRsXmlAll(0, "参数str1中code类型不支持");
                        }
                    }else {
                        return SplicingXml.splicingRsXmlAll(0, "鉴权失败");
                    }
                }else {
                    return SplicingXml.splicingRsXmlAll(0, "鉴权失败");
                }
            }
        }
    }
}
