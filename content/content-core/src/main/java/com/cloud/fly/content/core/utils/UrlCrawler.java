package com.cloud.fly.content.core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class UrlCrawler {

    private static Logger log = LoggerFactory.getLogger(UrlCrawler.class);


    public static final String FILE_CRAWLER = "crawlerHtmlCache/";

    public static String getHtmlCache(String url) {
        log.info("getHtmlCache:" + url);
        try {
            File file = new File(FILE_CRAWLER + url.hashCode() + ".html");
            if (!file.exists()) {
                log.error("get html file non exists . file=" + file.getAbsolutePath());
                return null;
            }
            String fileHtml = FileUtils.readFileToString(file, "UTF-8");
            return fileHtml;
        } catch (Exception exp) {
            log.error("get  html cache exp url=" + url, exp);
        }
        return null;
    }


    public static void setHtmlCache(String url, String htmlStr) {
        try {
            File file = new File(FILE_CRAWLER + url.hashCode() + ".html");
            FileUtils.writeStringToFile(file, htmlStr, "UTF-8");
        } catch (Exception exp) {
            log.error("set html cache exp url=" + url + ",htmlStr=" + htmlStr, exp);
        }
    }

    public static String crawlerHtml(String url) throws Exception {

        HttpClient httpClient = HttpClientBuilder.create().build();
        //使用HttpGet对象绑定url
        HttpGet httpGet = new HttpGet(url);
        //访问url获得响应消息封装在HttpResponse对象中
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //entity中是响应消息的实体
        HttpEntity entity = httpResponse.getEntity();
        //使用EntityUtils的toString获得url指定页面的字符串内容，即html本身
        String htmlStr = EntityUtils.toString(entity);
        return htmlStr;
    }

}
