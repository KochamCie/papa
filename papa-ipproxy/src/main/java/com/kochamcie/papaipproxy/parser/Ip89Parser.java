package com.kochamcie.papaipproxy.parser;
import com.kochamcie.common.http.IpRequest;
import com.kochamcie.common.model.ProxyInfo;
import com.kochamcie.common.parser.AbstractParser;
import lombok.extern.slf4j.Slf4j;
/**
 * @Author: rns
 * @Date: 2019/3/9 下午1:27
 * @Description: Ip66Source
 */
@Slf4j
public class Ip89Parser extends AbstractParser {

    public Ip89Parser(String url, Class clazz){
        this.url = url;
        this.clazz = clazz;
    }

    @Override
    protected void prepareIpRequest() {
        this.ipRequest = IpRequest.builder(this.url, this.clazz)
                .elementsQuery("table tr:gt(0)")
                .stringsQuery("td:eq(0)", "td:eq(1)", "td:eq(2)", "td:eq(4)")
                .build();
    }
}
