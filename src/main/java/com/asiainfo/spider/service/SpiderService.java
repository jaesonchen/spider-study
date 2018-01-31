package com.asiainfo.spider.service;

import java.util.List;

import com.asiainfo.spider.model.ZhihuUser;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月9日  上午9:35:52
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public interface SpiderService {
    
    /**
     * 抓取页面信息，返回抓取到的用户信息
     * 
     * @param url
     * @return
     */
    ZhihuUser crawler(String url);
    
    /**
     * 返回关注的url
     * 
     * @param url
     * @param page
     * @return
     */
    List<String> queryFollowingUrl(String url, int page);
}
