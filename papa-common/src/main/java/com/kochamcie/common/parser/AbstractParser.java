package com.kochamcie.common.parser;


import com.kochamcie.common.http.IpRequest;
import java.util.List;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午5:52
 * @Description: AbstractParser
 */
public abstract class AbstractParser<T> {

    protected String url;
    protected Class<T> clazz;


    protected IpRequest ipRequest;

    public final List<T> fetch(){
        prepareIpRequest();
        return ipRequest.request();
    }

    protected abstract void prepareIpRequest();


}
