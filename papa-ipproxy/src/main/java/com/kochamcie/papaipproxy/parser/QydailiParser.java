package com.kochamcie.papaipproxy.parser;

import com.kochamcie.common.http.IpRequest;
import com.kochamcie.common.model.ProxyInfo;
import com.kochamcie.common.parser.AbstractParser;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午7:18
 * @Description: QydailiParser
 */
@Slf4j
public class QydailiParser extends AbstractParser {

    public QydailiParser(String url, Class clazz){
        this.url = url;
        this.clazz = clazz;
    }

    @Override
    protected void prepareIpRequest() {
        this.ipRequest = IpRequest.builder(this.url, this.clazz)
                .elementsQuery("table tr:gt(1)")
                .stringsQuery("td:eq(0)", "td:eq(1)", "td:eq(4)", "td:eq(6)")
                .build();
    }
}
