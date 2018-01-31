package com.asiainfo.spider.model;

import java.io.Serializable;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月9日  上午9:41:31
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class ZhihuUser implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    String name;
    String business;
    String company;
    String position;
    String education;
    String major;
    Sex sex;
    int answerNum;
    int askNum;
    int postNum;
    int starNum;
    int thxNum;
    int followingNum;
    int followerNum;
    
    public int getAskNum() {
        return askNum;
    }
    public void setAskNum(int askNum) {
        this.askNum = askNum;
    }
    public int getPostNum() {
        return postNum;
    }
    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public Sex getSex() {
        return sex;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    public int getAnswerNum() {
        return answerNum;
    }
    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }
    public int getStarNum() {
        return starNum;
    }
    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }
    public int getThxNum() {
        return thxNum;
    }
    public void setThxNum(int thxNum) {
        this.thxNum = thxNum;
    }
    public int getFollowingNum() {
        return followingNum;
    }
    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }
    public int getFollowerNum() {
        return followerNum;
    }
    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }
    @Override
    public String toString() {
        return "ZhihuUser [name=" + name + ", business=" + business + ", company=" + company + ", position=" + position
                + ", education=" + education + ", major=" + major + ", sex=" + sex + ", answerNum=" + answerNum
                + ", askNum=" + askNum + ", postNum=" + postNum + ", starNum=" + starNum + ", thxNum=" + thxNum
                + ", followingNum=" + followingNum + ", followerNum=" + followerNum + "]";
    }
}
