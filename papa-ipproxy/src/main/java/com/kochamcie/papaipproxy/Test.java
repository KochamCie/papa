package com.kochamcie.papaipproxy;

import com.kochamcie.common.model.ProxyInfo;
import com.kochamcie.papaipproxy.parser.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午7:54
 * @Description: Test
 */
@Slf4j
public class Test {

    /**
     * # http://www.66ip.cn/5.html
     * # http://cn-proxy.com/
     * # http://www.89ip.cn/index_5.html
     * # http://www.xicidaili.com/nt/5.html
     * # http://www.qydaili.com/free/?action=china&page=5
     * # http://www.xicidaili.com/wt/5.html
     * @param args
     */


    public static void main(String [] args) {

        for (int i = 1; i < 10; i++) {
            new Ip66Parser("http://www.66ip.cn/" + i + ".html", ProxyInfo.class).fetch().forEach(proxyInfo -> log.info("{}", proxyInfo));
            new Ip89Parser("http://www.89ip.cn/index_" + i + ".html", ProxyInfo.class).fetch().forEach(proxyInfo -> log.info("{}", proxyInfo));
            new XicidailiParser("http://www.xicidaili.com/wt/" + i + ".html", ProxyInfo.class).fetch().forEach(proxyInfo -> log.info("{}", proxyInfo));
            new QydailiParser("http://www.qydaili.com/free/?action=china&page=" + i, ProxyInfo.class).fetch().forEach(proxyInfo -> log.info("{}", proxyInfo));
        }
        new CnproxyParser("http://cn-proxy.com/", ProxyInfo.class).fetch().forEach(proxyInfo -> log.info("{}", proxyInfo));

    }
}
