package com.kochamcie.common.http;

import com.kochamcie.common.model.BaseObject;
import com.kochamcie.common.model.ProxyInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: rns
 * @Date: 2019/3/9 上午11:21
 * @Description: IpRequest
 */
@Slf4j
@Data
public class IpRequest<T> extends BaseObject {

    private final String url;
    private final Connection.Method method;
    private final Map<String, String> headers;
    private final Map<String, String> cookies;
    private final Map<String, String> datas;
    private final String ip;
    private final int port;
    private final int timeout;
    private final String userAgent;
    private final Class<T> clazz;
    private String elementsQuery;
    private String[] stringsQuery;


    private IpRequest(Builder<T> builder){
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.cookies = builder.cookies;
        this.datas = builder.datas;
        this.ip = builder.ip;
        this.port = builder.port;
        this.timeout = builder.timeout;
        this.userAgent = builder.userAgent;
        this.clazz = builder.clazz;
        this.elementsQuery = builder.elementsQuery;
        this.stringsQuery = builder.stringsQuery;

    }


    public static class Builder<T> {

        private final String url;
        private Connection.Method method = Connection.Method.GET;
        private Map<String, String> headers;
        private Map<String, String> cookies;
        private Map<String, String> datas;
        private String ip;
        private int port;
        private int timeout;
        private String userAgent;
        private final Class<T> clazz;
        private String elementsQuery;
        private String[] stringsQuery;

        public Builder(String url, Class<T> clazz){
            this.url = url;
            this.clazz = clazz;
        }

        public Builder headers(Map<String, String> headers){
            this.headers = headers;
            return this;
        }

        public Builder cookies(Map<String, String> cookies){
            this.cookies = cookies;
            return this;
        }

        public Builder datas(Map<String, String> datas){
            this.datas = datas;
            return this;
        }

        public Builder proxy(String ip, int port){
            this.ip = ip;
            this.port = port;
            return this;
        }

        public Builder method(Connection.Method method){
            this.method = method;
            return this;
        }

        public Builder elementsQuery(String elementsQuery){
            this.elementsQuery = elementsQuery;
            return this;
        }

        public Builder stringsQuery(String... stringsQuery){
            this.stringsQuery = stringsQuery;
            return this;
        }

        public Builder timeout(int timeout){
            this.timeout = timeout;
            return this;
        }

        public Builder userAgent(String userAgent){
            this.userAgent = userAgent;
            return this;
        }

        public IpRequest build(){
            return new IpRequest(this);
        }


    }

    public List request() {
        return DocumentComponent.select(this.url, this.clazz, this.elementsQuery, this.stringsQuery);
    }


    public static <T> Builder<T> builder(String url, Class<T> clazz) {
        return new Builder<>(url, clazz);
    }

    public static void main(String [] args){
        String elementsQuery = "table tr:gt(1)";
        String[] stringsQuery = {"td:eq(0)", "td:eq(1)", "td:eq(3)"};
        IpRequest<ProxyInfo> ipRequest = new Builder("http://www.66ip.cn/5.html", ProxyInfo.class)
                .elementsQuery(elementsQuery)
                .stringsQuery(stringsQuery)
                .timeout(123)
                .build();

        List<ProxyInfo> list = ipRequest.request();
        log.info("{}", ipRequest);
        log.info("{}", list);
    }

}
