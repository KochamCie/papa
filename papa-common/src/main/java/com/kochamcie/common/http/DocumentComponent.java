package com.kochamcie.common.http;

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
 * @Date: 2019/3/9 上午9:38
 * @Description: DocumentComponent
 */
@Slf4j
public class DocumentComponent extends HttpComponent {

    protected static Elements select(Document document, String cssQuery) {
        return document.select(cssQuery);
    }

    protected static String select(Element element, String cssQuery) {
        return select(element, cssQuery, 0);
    }

    protected static String select(Element element, String cssQuery, int targetIndex) {
        return element.select(cssQuery).eq(targetIndex).text();
    }

    /**
     * @param document      document
     * @param clazz         clazz
     * @param elementsQuery elementsQuery
     * @param stringsQuery  stringsQuery
     * @param <T>           <T>
     * @return
     */
    protected static <T> List<T> select(Document document, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        List<T> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        Elements elements = select(document, elementsQuery);
        try {
            for (Element element : elements) {
                T obj = clazz.newInstance();
                for (int i = 0; i < stringsQuery.length; i++) {
                    String value = select(element, stringsQuery[i]);
                    Field field = fields[i];
                    field.setAccessible(true);
                    field.set(obj, value);

                }
                list.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected static <T> List<T> select(String url, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, EMPTY_MAP, EMPTY_MAP, EMPTY_MAP, null, 0, null, clazz, elementsQuery, stringsQuery);

    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, headers, EMPTY_MAP, EMPTY_MAP, null, 0, null, clazz, elementsQuery, stringsQuery);
    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Map<String, String> cookies, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, headers, cookies, EMPTY_MAP, null, 0, null, clazz, elementsQuery, stringsQuery);
    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, headers, cookies, datas, null, 0, null, clazz, elementsQuery, stringsQuery);

    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, Proxy proxy, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, headers, cookies, datas, proxy, 0, null, clazz, elementsQuery, stringsQuery);

    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, Proxy proxy, int timeout, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        return select(url, headers, cookies, datas, proxy, timeout, null, clazz, elementsQuery, stringsQuery);

    }

    protected static <T> List<T> select(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> datas, Proxy proxy, int timeout, String userAgent, Class<T> clazz, String elementsQuery, String... stringsQuery) {
        Document document = get(url, headers, cookies, datas, proxy, timeout, userAgent);
        if (null == document) return EMPTY_LIST;
        return select(document, clazz, elementsQuery, stringsQuery);
    }


}
