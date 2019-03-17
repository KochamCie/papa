package com.kochamcie.papaipproxy;

import com.kochamcie.common.http.IpRequest;
import com.kochamcie.papaipproxy.healthcheck.IpCheck;
import com.kochamcie.papaipproxy.parser.*;
import com.kochamcie.papastore.entity.ProxyInfo;
import com.kochamcie.papastore.service.ProxyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午8:30
 * @Description: ProxyInfoAPI
 */
@Slf4j
@RestController
public class ProxyInfoAPI {

    @Autowired
    private ProxyInfoService proxyInfoService;

    /**
     * # http://www.66ip.cn/5.html
     * # http://cn-proxy.com/
     * # http://www.89ip.cn/index_5.html
     * # http://www.xicidaili.com/nt/5.html
     * # http://www.qydaili.com/free/?action=china&page=5
     * # http://www.xicidaili.com/wt/5.html
     */
    @RequestMapping(value = "/init")
    public void init() {
        List<ProxyInfo> list = new ArrayList<>();
        for (int i = 1; i < 40; i++) {
            list = new Ip66Parser("http://www.66ip.cn/" + i + ".html", ProxyInfo.class).fetch();
            log.info("{}", list.size());
            proxyInfoService.batchSave(list);
            list = new Ip89Parser("http://www.89ip.cn/index_" + i + ".html", ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);
            log.info("{}", list.size());
            list = new XicidailiParser("http://www.xicidaili.com/wt/" + i + ".html", ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);
            log.info("{}", list.size());
            list = new QydailiParser("http://www.qydaili.com/free/?action=china&page=" + i, ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);
            log.info("{}", list.size());
        }
        list = new CnproxyParser("http://cn-proxy.com/", ProxyInfo.class).fetch();
        proxyInfoService.batchSave(list);
    }

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public Object ip() {

        String elementsQuery = "table tr:gt(1)";
        String[] stringsQuery = {"td:eq(0)", "td:eq(1)", "td:eq(3)"};
        IpRequest<com.kochamcie.common.model.ProxyInfo> ipRequest = new IpRequest.Builder("http://www.66ip.cn/5.html", com.kochamcie.common.model.ProxyInfo.class)
                .elementsQuery(elementsQuery)
                .stringsQuery(stringsQuery)
                .timeout(123)
//                .proxy("202.62.60.96", 8080)
                .proxy("112.85.129.102", 9999)
                .build();

        List<com.kochamcie.common.model.ProxyInfo> list = ipRequest.request();
        log.info("{}", ipRequest);
        log.info("{}", list);
        return list;
    }


    @RequestMapping(value = "/boom")
    public Object boom() {
        return "boom";
    }


    @RequestMapping(value = "/check/{page}/{pageSize}")
    public Object test(@PathVariable int page, @PathVariable int pageSize) {
        List<String> result = new ArrayList<>();

        Page<ProxyInfo> proxyInfoPage = proxyInfoService.proxyInfoList(page, pageSize);
        for (ProxyInfo proxyInfo : proxyInfoPage.getContent()) {
            boolean check = IpCheck.check(proxyInfo.getIp(), Integer.parseInt(proxyInfo.getPort()));
            if(!check){
                result.add(proxyInfo.getIp());
            }
            log.info("{} result is :{}", proxyInfo.getIp(), check);
        }
        return result;
    }


}
