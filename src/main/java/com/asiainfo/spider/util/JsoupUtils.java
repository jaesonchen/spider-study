package com.asiainfo.spider.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月8日  下午5:34:12
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class JsoupUtils {

    public static void main(String[] args) throws IOException {
        
        HttpUtils.setProxy("proxy.asiainfo.com", 8080);
        Document document = getDocument("http://www.baidu.com", 10000);
        System.out.println(document);
    }
    
    /**
     * 返回jsoup格式document
     * 
     * @param url
     * @param timeout
     * @return
     * @throws IOException
     */
    public static Document getDocument(String url, int timeout) throws IOException {
        return Jsoup.connect(url).timeout(timeout).get();
    }
}
