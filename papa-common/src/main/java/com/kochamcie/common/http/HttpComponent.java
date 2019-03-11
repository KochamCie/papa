package com.kochamcie.common.http;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rns
 * @Date: 2019/3/9 上午9:14
 * @Description: HttpComponent
 */
@Slf4j
public class HttpComponent {

    protected static final Map<String, String> EMPTY_MAP = new HashMap<>();
    protected static final List EMPTY_LIST = new ArrayList();

    protected static Document post(String url) {
        try {
            Document doc = Jsoup.connect(url).post();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected static Document get(String url) {
        return request(url, EMPTY_MAP, EMPTY_MAP, EMPTY_MAP, Connection.Method.GET, null, -1, 0, null);
    }

    protected static Document get(String url, Map<String, String> headers) {
        return request(url, headers, EMPTY_MAP, EMPTY_MAP, Connection.Method.GET, null, -1, 0, null);
    }

    protected static Document get(String url, Map<String, String> headers, Map<String, String> cookies) {
        return request(url, headers, cookies, EMPTY_MAP, Connection.Method.GET, null, -1, 0, null);
    }

    protected static Document get(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas) {
        return request(url, headers, cookies, datas, Connection.Method.GET, null, -1, 0, null);
    }

    protected static Document get(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, String ip, int port) {
        return request(url, headers, cookies, datas, Connection.Method.GET, ip, port, 0, null);
    }

    protected static Document get(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, String ip, int port, int timeout) {
        return request(url, headers, cookies, datas, Connection.Method.GET, ip, port, timeout, null);
    }

    protected static Document get(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, String ip, int port, int timeout, String userAgent) {
        return request(url, headers, cookies, datas, Connection.Method.GET, ip, port, timeout, userAgent);
    }


    /**
     * @param url       target url
     * @param headers   headers
     * @param cookies   cookies
     * @param datas     datas
     * @param method    method
     * @param proxy     proxy
     * @param timeout   timeout
     * @param userAgent userAgent
     * @return
     */
//    private static Document request(String url,
//                                    Map<String, String> headers,
//                                    Map<String, String> cookies,
//                                    Map<String, String> datas,
//                                    Connection.Method method,
//                                    Proxy proxy,
//                                    int timeout,
//                                    String userAgent) {
//        try {
//            Connection connection = Jsoup
//                    .connect(url)
//                    .headers(headers)
//                    .cookies(cookies)
//                    .data(datas)
//                    .method(method)
//                    .proxy(proxy);
//            if (null != userAgent) connection.userAgent(userAgent);
//            if (0 != timeout) connection.timeout(timeout);
//            return connection.execute().parse();
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("request exception:{}", e);
//        }
//        return null;
//    }


    /**
     * @param url       target url
     * @param headers   headers
     * @param cookies   cookies
     * @param datas     datas
     * @param method    method
     * @param ip        ip
     * @param port      port
     * @param timeout   timeout
     * @param userAgent userAgent
     * @return
     */
    private static Document request(String url,
                                    Map<String, String> headers,
                                    Map<String, String> cookies,
                                    Map<String, String> datas,
                                    Connection.Method method,
                                    String ip,
                                    int port,
                                    int timeout,
                                    String userAgent) {
        try {
            Connection connection = Jsoup
                    .connect(url)
                    .headers(headers)
                    .cookies(cookies)
                    .data(datas)
                    .method(method);
            if (null != userAgent) connection.userAgent(userAgent);
            if (0 != timeout) connection.timeout(timeout);
            if (-1 != port) connection.proxy(ip, port);
            return connection.execute().parse();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("request exception:{}", e);
        }
        return null;
    }
}
