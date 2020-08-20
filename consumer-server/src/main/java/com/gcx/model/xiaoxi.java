package com.gcx.model;

import javax.persistence.*;

public class xiaoxi {
    /**
     * 消息编号
     */
    @Id
    private String xxid;

    /**
     * 企业id
     */
    private String entid;

    private String entname;

    /**
     * 部门id
     */
    private String deptid;

    /**
     * 部门名称
     */
    private String deptname;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 发布时间 yyyy-mm-dd hh:MM:ss
     */
    private String pubtime;

    /**
     * 添加时间 本地生成,转换为日期格式String 
     */
    private String addtime;

    /**
     * 发布状态 0:未读 1:已读 默认为0
     */
    private String status;

    /**
     * 消息类型
     */
    private String type;

    private String deleteflag;

    /**
     * 登录名字
     */
    private String r1;

    private String r2;

    private String r3;

    private String r4;

    private String r5;

    /**
     * 消息内容
     */
    private String content;

    private String r6;

    /**
     * 获取消息编号
     *
     * @return xxid - 消息编号
     */
    public String getXxid() {
        return xxid;
    }

    /**
     * 设置消息编号
     *
     * @param xxid 消息编号
     */
    public void setXxid(String xxid) {
        this.xxid = xxid;
    }

    /**
     * 获取企业id
     *
     * @return entid - 企业id
     */
    public String getEntid() {
        return entid;
    }

    /**
     * 设置企业id
     *
     * @param entid 企业id
     */
    public void setEntid(String entid) {
        this.entid = entid;
    }

    /**
     * @return entname
     */
    public String getEntname() {
        return entname;
    }

    /**
     * @param entname
     */
    public void setEntname(String entname) {
        this.entname = entname;
    }

    /**
     * 获取部门id
     *
     * @return deptid - 部门id
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 设置部门id
     *
     * @param deptid 部门id
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * 获取部门名称
     *
     * @return deptname - 部门名称
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * 设置部门名称
     *
     * @param deptname 部门名称
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    /**
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取用户姓名
     *
     * @return username - 用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户姓名
     *
     * @param username 用户姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取消息标题
     *
     * @return title - 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置消息标题
     *
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取发布时间 yyyy-mm-dd hh:MM:ss
     *
     * @return pubtime - 发布时间 yyyy-mm-dd hh:MM:ss
     */
    public String getPubtime() {
        return pubtime;
    }

    /**
     * 设置发布时间 yyyy-mm-dd hh:MM:ss
     *
     * @param pubtime 发布时间 yyyy-mm-dd hh:MM:ss
     */
    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    /**
     * 获取添加时间 本地生成,转换为日期格式String 
     *
     * @return addtime - 添加时间 本地生成,转换为日期格式String 
     */
    public String getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间 本地生成,转换为日期格式String 
     *
     * @param addtime 添加时间 本地生成,转换为日期格式String 
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取发布状态 0:未读 1:已读 默认为0
     *
     * @return status - 发布状态 0:未读 1:已读 默认为0
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置发布状态 0:未读 1:已读 默认为0
     *
     * @param status 发布状态 0:未读 1:已读 默认为0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取消息类型
     *
     * @return type - 消息类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置消息类型
     *
     * @param type 消息类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return deleteflag
     */
    public String getDeleteflag() {
        return deleteflag;
    }

    /**
     * @param deleteflag
     */
    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * 获取登录名字
     *
     * @return r1 - 登录名字
     */
    public String getR1() {
        return r1;
    }

    /**
     * 设置登录名字
     *
     * @param r1 登录名字
     */
    public void setR1(String r1) {
        this.r1 = r1;
    }

    /**
     * @return r2
     */
    public String getR2() {
        return r2;
    }

    /**
     * @param r2
     */
    public void setR2(String r2) {
        this.r2 = r2;
    }

    /**
     * @return r3
     */
    public String getR3() {
        return r3;
    }

    /**
     * @param r3
     */
    public void setR3(String r3) {
        this.r3 = r3;
    }

    /**
     * @return r4
     */
    public String getR4() {
        return r4;
    }

    /**
     * @param r4
     */
    public void setR4(String r4) {
        this.r4 = r4;
    }

    /**
     * @return r5
     */
    public String getR5() {
        return r5;
    }

    /**
     * @param r5
     */
    public void setR5(String r5) {
        this.r5 = r5;
    }

    /**
     * 获取消息内容
     *
     * @return content - 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     *
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return r6
     */
    public String getR6() {
        return r6;
    }

    /**
     * @param r6
     */
    public void setR6(String r6) {
        this.r6 = r6;
    }
}