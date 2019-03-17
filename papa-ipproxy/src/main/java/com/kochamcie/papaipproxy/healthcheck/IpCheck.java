package com.kochamcie.papaipproxy.healthcheck;

import com.kochamcie.common.http.IpRequest;
import com.kochamcie.common.model.ProxyInfo;

import java.util.List;

/**
 * @Author: rns
 * @Date: 2019/3/12 上午9:45
 * @Description: IpCheck
 */
public class IpCheck {

    public static boolean check(String ip, int port) {

        try {
            String elementsQuery = "body";
            IpRequest<ProxyInfo> ipRequest = new IpRequest.Builder("http://localhost:8080/boom", ProxyInfo.class)
                    .elementsQuery(elementsQuery)
                    .stringsQuery(elementsQuery)
                    //                .proxy("202.62.60.96", 8080)
                    .proxy(ip, port)
                    .build();

            List<ProxyInfo> list = ipRequest.request();
            return !list.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
