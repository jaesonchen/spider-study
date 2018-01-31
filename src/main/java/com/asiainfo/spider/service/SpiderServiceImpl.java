package com.asiainfo.spider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.spider.model.Sex;
import com.asiainfo.spider.model.ZhihuUser;
import com.asiainfo.spider.util.HttpUtils;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月9日  上午10:04:44
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class SpiderServiceImpl implements SpiderService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    /* 
     * TODO
     * @param url
     * @return
     * @see com.asiainfo.spider.service.SpiderService#crawler(java.lang.String)
     */
    @Override
    public ZhihuUser crawler(String url) {

        logger.info("抓取 url:{}", url);
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        ZhihuUser user = new ZhihuUser();
        //抓取网页
        String html = HttpUtils.getCotent(url, 10000);
        //解析网页
        Element content = Jsoup.parse(html);
        //姓名
        user.setName(content.select(".ProfileHeader-name").first().text());
        //行业 公司 职位
        String info = content.select(".ProfileHeader-infoItem").first().text();
        if (!StringUtils.isEmpty(info)) {
            String[] arr = info.split("\\s+");
            //行业
            user.setBusiness(arr[0]);
            //公司
            if (arr.length > 1) {
                user.setCompany(arr[1]);
            }
            //职位
            if (arr.length > 2) {
                user.setPosition(arr[2]);
            }
        }
        info = content.select(".ProfileHeader-infoItem").size() > 1 ? content.select(".ProfileHeader-infoItem").get(1).text() : null;
        if (!StringUtils.isEmpty(info)) {
            String[] arr = info.split("\\s+");
            //学校
            user.setEducation(arr[0]);
            //专业
            if (arr.length > 1) {
                user.setMajor(arr[1]);
            }
        }
        
        //看关注他中有无关键字，判断性别
        String sexString = content.select(".FollowButton").first().text();
        if (sexString.contains("关注他")) {
            user.setSex(Sex.MALE);
        } else if (sexString.contains("关注她")) {
            user.setSex(Sex.FEMAIL);
        } else {
            user.setSex(Sex.UNKNOWN);
        }
        
        // 回答、提问、文章数量
        for (Element ele : content.select(".Tabs-item")) {
            String attr = ele.attr("aria-controls");
            if ("Profile-answers".equals(attr)) {
                user.setAnswerNum(Integer.parseInt(ele.select(".Tabs-meta").text().replace(",", "").trim()));
            } else if ("Profile-asks".equals(attr)) {
                user.setAskNum(Integer.parseInt(ele.select(".Tabs-meta").text().replace(",", "").trim()));
            } else if ("Profile-posts".equals(attr)) {
                user.setPostNum(Integer.parseInt(ele.select(".Tabs-meta").text().replace(",", "").trim()));
            }
        }
        
        //收藏、感谢数量
        String thx = content.select(".Profile-sideColumnItemValue").text();
        Pattern pattern = Pattern.compile("(\\d+(,\\d{3})*) 次感谢，(\\d+(,\\d{3})*)");
        Matcher matcher = pattern.matcher(thx);
        if (matcher.find()) {
            user.setThxNum(Integer.parseInt(matcher.group(1).replace(",", "").trim()));
            user.setStarNum(Integer.parseInt(matcher.group(3).replace(",", "").trim()));
        }
        
        //关注
        String following = content.select(".FollowshipCard-counts").select(".NumberBoard-itemValue").first().text();
        user.setFollowingNum(Integer.parseInt(following.replace(",", "").trim()));
        String follower = content.select(".FollowshipCard-counts").select(".NumberBoard-itemValue").last().text();
        user.setFollowerNum(Integer.parseInt(follower.replace(",", "").trim()));
        
        logger.info("抓取的用户信息：{}", user);
        return user;
    }

    /* 
     * TODO
     * @param url
     * @param page
     * @return
     * @see com.asiainfo.spider.service.SpiderService#queryFollowingUrl(java.lang.String, int)
     */
    @Override
    public List<String> queryFollowingUrl(String url, int page) {

        String followingUrl = url + "/following" + (page > 1 ? "?page=" + page : "");
        String html = HttpUtils.getCotent(followingUrl, 10000);
        System.out.println(html);
        //解析网页
        Element content = Jsoup.parse(html);
        Elements followings = content.select(".List-item");
        List<String> result = new ArrayList<>();
        for (Element ele : followings) {
            result.add("https://www.zhihu.com" + ele.select("a[href]").get(0).attr("href"));
        }
        return result;
    }
    
    public static void main(String[] args) {
        HttpUtils.setProxy("proxy.asiainfo.com", 8080);
        SpiderService service = new SpiderServiceImpl();
        //service.crawler("https://www.zhihu.com/people/chenghan/activities");
        List<String> list = service.queryFollowingUrl("https://www.zhihu.com/people/chenghan", 1);
        for (String str : list) {
            System.out.println(str);
        }
    }
}
