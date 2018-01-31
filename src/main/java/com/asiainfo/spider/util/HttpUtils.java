package com.asiainfo.spider.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月8日  下午5:38:23
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class HttpUtils {
    
    public static void main(String[] args) throws IOException {
        
        HttpUtils.setProxy("proxy.asiainfo.com", 8080);
        String content = getCotent("http://www.baidu.com", 10000);
        System.out.println(content);
    }
    
    /**
     * 代理设置
     * 
     * @param ip
     * @param port
     */
    public static void setProxy(String ip, int port) {
        
        System.getProperties().setProperty("proxySet", "true");   
        System.getProperties().setProperty("http.proxyHost", ip);  
        System.getProperties().setProperty("http.proxyPort", String.valueOf(port));
    }
    
    /**
     * 返回url的内容
     * 
     * @param url
     * @param timeout
     * @return
     */
    public static String getCotent(String url, int timeout) {
        
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (Exception ex) {}
        }
        return result.toString();
    }
}
