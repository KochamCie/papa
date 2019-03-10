package com.kochamcie.papaipproxy;

import com.kochamcie.papaipproxy.parser.*;
import com.kochamcie.papastore.entity.ProxyInfo;
import com.kochamcie.papastore.service.ProxyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/init")
    public void init(){
        List<ProxyInfo> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list = new Ip66Parser("http://www.66ip.cn/" + i + ".html", ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);
            list = new Ip89Parser("http://www.89ip.cn/index_" + i + ".html", ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);

            list = new XicidailiParser("http://www.xicidaili.com/wt/" + i + ".html", ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);

            list = new QydailiParser("http://www.qydaili.com/free/?action=china&page=" + i, ProxyInfo.class).fetch();
            proxyInfoService.batchSave(list);

        }
        list = new CnproxyParser("http://cn-proxy.com/", ProxyInfo.class).fetch();
        proxyInfoService.batchSave(list);
    }
}
