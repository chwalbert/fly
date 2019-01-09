package com.cloud.fly.content.core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class UrlCrawler {

    private static Logger log = LoggerFactory.getLogger(UrlCrawler.class);


    public static final String FILE_CRAWLER = "crawlerHtmlCache/";

    public static String getHtml(String url, boolean isCache) {
        log.info("getHtml: {} {} ", isCache, url);
        String html = null;
        try {
            File file = new File(FILE_CRAWLER + url.hashCode() + ".html");
            if (file.exists()) {
                log.info("getHtml is cache");
                html = FileUtils.readFileToString(file, "UTF-8");
            } else {
                html = getJsoupHtml(url);
                setHtmlCache(url, html);
            }
        } catch (Exception exp) {
            log.error("get  html cache exp", exp);
        }
        log.info(html);
        return html;
    }


    public static void setHtmlCache(String url, String htmlStr) {
        try {
            File file = new File(FILE_CRAWLER + url.hashCode() + ".html");
            FileUtils.writeStringToFile(file, htmlStr, "UTF-8");
        } catch (Exception exp) {
            log.error("set html cache exp url=" + url + ",htmlStr=" + htmlStr, exp);
        }
    }

    public static String getHttpClientHtml(String url) throws Exception {

        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
        cookie.setDomain(".github.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        HttpClient httpClient = HttpClientBuilder.create().build();
        //使用HttpGet对象绑定url
        //设置cookies信息

        HttpGet httpGet = new HttpGet(url);
        //访问url获得响应消息封装在HttpResponse对象中
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //entity中是响应消息的实体
        HttpEntity entity = httpResponse.getEntity();
        //使用EntityUtils的toString获得url指定页面的字符串内容，即html本身
        String htmlStr = EntityUtils.toString(entity);
        return htmlStr;
    }

    public static String getJsoupHtml(String url) throws Exception {
        Connection connect = Jsoup.connect(url);
        Map<String, String> header = new HashMap();
        header.put("Host", "https://www.airasia.com/flights/japan");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        Connection data = connect.headers(header);
        Document document = data.get();
        return document.html();
    }
    public static  void main(String[] agr) throws Exception {

        String url="https://www.airasia.com/booking/select/zh/cn/KUA/AOR/2019-01-15/2019-01-18/1/0/0/R/N/CNY/SC";
    }

}
