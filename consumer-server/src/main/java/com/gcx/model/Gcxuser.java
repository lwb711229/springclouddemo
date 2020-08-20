package com.gcx.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "GCX_USER")
public class Gcxuser {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "USER_ID")
    private BigDecimal userId;

    /**
     * 用户名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 用户手机号码
     */
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 用户状态 
0：正常  
1： 修改资料，账号锁定 
2：账号异常 
3：账号锁定（修改资料审核不通过）
4：初次申请简单账号，没有详细资料
7：快捷登录完善通过
8：快捷登录完善驳回
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 帐号类别 0 个人 1 企业  2 民间借贷3 权限管理 
4 销售系统
5 财务
6 信用分析
7 数据审核
8 政府
9 协会
10 gcx专用（新版国诚信官网）
11 研究院官网专用
12 多语言内部专用
13 园区
     */
    @Column(name = "ACC_CATE")
    private Short accCate;

    /**
     * 关联id 根据acc_cate帐号类别关联个人/企业/员工表
0 个人id 1 企业id 2 员工id

     */
    @Column(name = "RELATED_ID")
    private BigDecimal relatedId;

    /**
     * 注册时间
     */
    @Column(name = "REGIST_TIME")
    private Date registTime;

    /**
     * 上次登录时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 最后修改密码时间
     */
    @Column(name = "MODIFY_PWD_TIME")
    private Date modifyPwdTime;

    /**
     * 登录次数
     */
    @Column(name = "LOGIN_NUM")
    private Integer loginNum;

    /**
     * 用户来源
     */
    @Column(name = "SOURCE")
    private String source;

    /**
     * 头像图片地址
     */
    @Column(name = "HEAD_PORTRAITS")
    private String headPortraits;

    /**
     * 1.手机 2.邮箱
     */
    @Column(name = "REGIST_TYPE")
    private String registType;

    /**
     * 逻辑删除：0.有效1.删除
     */
    @Column(name = "DELETE_FLAG")
    private Integer deleteFlag;

    /**
     * 用户分组
gcx官网:"gcx_user"
研究院："gcx_icc"
本公司员工："gcx_gs"
     */
    @Column(name = "USER_GROUP")
    private String userGroup;

    /**
     * 用户真实姓名、公司名、协会名等
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 是否主账号（是：否）
     */
    @Column(name = "Z1")
    private String z1;

    /**
     * gcx官网、研究院官网等账号特殊处理字段
gcx官网:"gcx_user"
     */
    @Column(name = "Z2")
    private String z2;

    /**
     * 重大会议 - 单位名称
     */
    @Column(name = "Z3")
    private String z3;

    /**
     * 系统名称
     */
    @Column(name = "Z4")
    private String z4;

    /**
     * 重大会议 - 职务
     */
    @Column(name = "Z5")
    private String z5;

    /**
     * 重大会议 - 常驻地址
     */
    @Column(name = "Z6")
    private String z6;

    /**
     * 系统ID
     */
    @Column(name = "Z7")
    private String z7;

    /**
     * 政务诚信(统计）-部门ID
     */
    @Column(name = "Z8")
    private String z8;

    /**
     * 销售人员账户//后台审核发布者，审核者
     */
    @Column(name = "Z9")
    private String z9;

    /**
     * 快捷注册 标识   未注册：0  已注册：1
     */
    @Column(name = "Z10")
    private String z10;

    /**
     * 部门
     */
    @Column(name = "DEPT")
    private String dept;

    /**
     * 部门ID
     */
    @Column(name = "DEPT_ID")
    private String deptId;

    /**
     * 职位
     */
    @Column(name = "POST")
    private String post;

    /**
     * 职位ID
     */
    @Column(name = "POST_ID")
    private String postId;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * 负责区域（以“；”拼接）
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 负责区域ID（联动末级ID，以“；”拼接）
     */
    @Column(name = "AREA_ID")
    private String areaId;

    /**
     * 主账号ID
     */
    @Column(name = "MAIN_ID")
    private String mainId;

    /**
     * 备注
     */
    @Column(name = "OTHER")
    private String other;

    /**
     * 获取用户编号
     *
     * @return USER_ID - 用户编号
     */
    public BigDecimal getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return USER_NAME - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户手机号码
     *
     * @return MOBILE_NUMBER - 用户手机号码
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 设置用户手机号码
     *
     * @param mobileNumber 用户手机号码
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户状态 
0：正常  
1： 修改资料，账号锁定 
2：账号异常 
3：账号锁定（修改资料审核不通过）
4：初次申请简单账号，没有详细资料
7：快捷登录完善通过
8：快捷登录完善驳回
     *
     * @return STATUS - 用户状态 
0：正常  
1： 修改资料，账号锁定 
2：账号异常 
3：账号锁定（修改资料审核不通过）
4：初次申请简单账号，没有详细资料
7：快捷登录完善通过
8：快捷登录完善驳回
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置用户状态 
0：正常  
1： 修改资料，账号锁定 
2：账号异常 
3：账号锁定（修改资料审核不通过）
4：初次申请简单账号，没有详细资料
7：快捷登录完善通过
8：快捷登录完善驳回
     *
     * @param status 用户状态 
0：正常  
1： 修改资料，账号锁定 
2：账号异常 
3：账号锁定（修改资料审核不通过）
4：初次申请简单账号，没有详细资料
7：快捷登录完善通过
8：快捷登录完善驳回
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取帐号类别 0 个人 1 企业  2 民间借贷3 权限管理 
4 销售系统
5 财务
6 信用分析
7 数据审核
8 政府
9 协会
10 gcx专用（新版国诚信官网）
11 研究院官网专用
12 多语言内部专用
13 园区
     *
     * @return ACC_CATE - 帐号类别 0 个人 1 企业  2 民间借贷3 权限管理 
4 销售系统
5 财务
6 信用分析
7 数据审核
8 政府
9 协会
10 gcx专用（新版国诚信官网）
11 研究院官网专用
12 多语言内部专用
13 园区
     */
    public Short getAccCate() {
        return accCate;
    }

    /**
     * 设置帐号类别 0 个人 1 企业  2 民间借贷3 权限管理 
4 销售系统
5 财务
6 信用分析
7 数据审核
8 政府
9 协会
10 gcx专用（新版国诚信官网）
11 研究院官网专用
12 多语言内部专用
13 园区
     *
     * @param accCate 帐号类别 0 个人 1 企业  2 民间借贷3 权限管理 
4 销售系统
5 财务
6 信用分析
7 数据审核
8 政府
9 协会
10 gcx专用（新版国诚信官网）
11 研究院官网专用
12 多语言内部专用
13 园区
     */
    public void setAccCate(Short accCate) {
        this.accCate = accCate;
    }

    /**
     * 获取关联id 根据acc_cate帐号类别关联个人/企业/员工表
0 个人id 1 企业id 2 员工id

     *
     * @return RELATED_ID - 关联id 根据acc_cate帐号类别关联个人/企业/员工表
0 个人id 1 企业id 2 员工id

     */
    public BigDecimal getRelatedId() {
        return relatedId;
    }

    /**
     * 设置关联id 根据acc_cate帐号类别关联个人/企业/员工表
0 个人id 1 企业id 2 员工id

     *
     * @param relatedId 关联id 根据acc_cate帐号类别关联个人/企业/员工表
0 个人id 1 企业id 2 员工id

     */
    public void setRelatedId(BigDecimal relatedId) {
        this.relatedId = relatedId;
    }

    /**
     * 获取注册时间
     *
     * @return REGIST_TIME - 注册时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册时间
     *
     * @param registTime 注册时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * 获取上次登录时间
     *
     * @return LAST_LOGIN_TIME - 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastLoginTime 上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后修改密码时间
     *
     * @return MODIFY_PWD_TIME - 最后修改密码时间
     */
    public Date getModifyPwdTime() {
        return modifyPwdTime;
    }

    /**
     * 设置最后修改密码时间
     *
     * @param modifyPwdTime 最后修改密码时间
     */
    public void setModifyPwdTime(Date modifyPwdTime) {
        this.modifyPwdTime = modifyPwdTime;
    }

    /**
     * 获取登录次数
     *
     * @return LOGIN_NUM - 登录次数
     */
    public Integer getLoginNum() {
        return loginNum;
    }

    /**
     * 设置登录次数
     *
     * @param loginNum 登录次数
     */
    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 获取用户来源
     *
     * @return SOURCE - 用户来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置用户来源
     *
     * @param source 用户来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取头像图片地址
     *
     * @return HEAD_PORTRAITS - 头像图片地址
     */
    public String getHeadPortraits() {
        return headPortraits;
    }

    /**
     * 设置头像图片地址
     *
     * @param headPortraits 头像图片地址
     */
    public void setHeadPortraits(String headPortraits) {
        this.headPortraits = headPortraits;
    }

    /**
     * 获取1.手机 2.邮箱
     *
     * @return REGIST_TYPE - 1.手机 2.邮箱
     */
    public String getRegistType() {
        return registType;
    }

    /**
     * 设置1.手机 2.邮箱
     *
     * @param registType 1.手机 2.邮箱
     */
    public void setRegistType(String registType) {
        this.registType = registType;
    }

    /**
     * 获取逻辑删除：0.有效1.删除
     *
     * @return DELETE_FLAG - 逻辑删除：0.有效1.删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置逻辑删除：0.有效1.删除
     *
     * @param deleteFlag 逻辑删除：0.有效1.删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取用户分组
gcx官网:"gcx_user"
研究院："gcx_icc"
本公司员工："gcx_gs"
     *
     * @return USER_GROUP - 用户分组
gcx官网:"gcx_user"
研究院："gcx_icc"
本公司员工："gcx_gs"
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * 设置用户分组
gcx官网:"gcx_user"
研究院："gcx_icc"
本公司员工："gcx_gs"
     *
     * @param userGroup 用户分组
gcx官网:"gcx_user"
研究院："gcx_icc"
本公司员工："gcx_gs"
     */
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * 获取用户真实姓名、公司名、协会名等
     *
     * @return REAL_NAME - 用户真实姓名、公司名、协会名等
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置用户真实姓名、公司名、协会名等
     *
     * @param realName 用户真实姓名、公司名、协会名等
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取是否主账号（是：否）
     *
     * @return Z1 - 是否主账号（是：否）
     */
    public String getZ1() {
        return z1;
    }

    /**
     * 设置是否主账号（是：否）
     *
     * @param z1 是否主账号（是：否）
     */
    public void setZ1(String z1) {
        this.z1 = z1;
    }

    /**
     * 获取gcx官网、研究院官网等账号特殊处理字段
gcx官网:"gcx_user"
     *
     * @return Z2 - gcx官网、研究院官网等账号特殊处理字段
gcx官网:"gcx_user"
     */
    public String getZ2() {
        return z2;
    }

    /**
     * 设置gcx官网、研究院官网等账号特殊处理字段
gcx官网:"gcx_user"
     *
     * @param z2 gcx官网、研究院官网等账号特殊处理字段
gcx官网:"gcx_user"
     */
    public void setZ2(String z2) {
        this.z2 = z2;
    }

    /**
     * 获取重大会议 - 单位名称
     *
     * @return Z3 - 重大会议 - 单位名称
     */
    public String getZ3() {
        return z3;
    }

    /**
     * 设置重大会议 - 单位名称
     *
     * @param z3 重大会议 - 单位名称
     */
    public void setZ3(String z3) {
        this.z3 = z3;
    }

    /**
     * 获取系统名称
     *
     * @return Z4 - 系统名称
     */
    public String getZ4() {
        return z4;
    }

    /**
     * 设置系统名称
     *
     * @param z4 系统名称
     */
    public void setZ4(String z4) {
        this.z4 = z4;
    }

    /**
     * 获取重大会议 - 职务
     *
     * @return Z5 - 重大会议 - 职务
     */
    public String getZ5() {
        return z5;
    }

    /**
     * 设置重大会议 - 职务
     *
     * @param z5 重大会议 - 职务
     */
    public void setZ5(String z5) {
        this.z5 = z5;
    }

    /**
     * 获取重大会议 - 常驻地址
     *
     * @return Z6 - 重大会议 - 常驻地址
     */
    public String getZ6() {
        return z6;
    }

    /**
     * 设置重大会议 - 常驻地址
     *
     * @param z6 重大会议 - 常驻地址
     */
    public void setZ6(String z6) {
        this.z6 = z6;
    }

    /**
     * 获取系统ID
     *
     * @return Z7 - 系统ID
     */
    public String getZ7() {
        return z7;
    }

    /**
     * 设置系统ID
     *
     * @param z7 系统ID
     */
    public void setZ7(String z7) {
        this.z7 = z7;
    }

    /**
     * 获取政务诚信(统计）-部门ID
     *
     * @return Z8 - 政务诚信(统计）-部门ID
     */
    public String getZ8() {
        return z8;
    }

    /**
     * 设置政务诚信(统计）-部门ID
     *
     * @param z8 政务诚信(统计）-部门ID
     */
    public void setZ8(String z8) {
        this.z8 = z8;
    }

    /**
     * 获取销售人员账户//后台审核发布者，审核者
     *
     * @return Z9 - 销售人员账户//后台审核发布者，审核者
     */
    public String getZ9() {
        return z9;
    }

    /**
     * 设置销售人员账户//后台审核发布者，审核者
     *
     * @param z9 销售人员账户//后台审核发布者，审核者
     */
    public void setZ9(String z9) {
        this.z9 = z9;
    }

    /**
     * 获取快捷注册 标识   未注册：0  已注册：1
     *
     * @return Z10 - 快捷注册 标识   未注册：0  已注册：1
     */
    public String getZ10() {
        return z10;
    }

    /**
     * 设置快捷注册 标识   未注册：0  已注册：1
     *
     * @param z10 快捷注册 标识   未注册：0  已注册：1
     */
    public void setZ10(String z10) {
        this.z10 = z10;
    }

    /**
     * 获取部门
     *
     * @return DEPT - 部门
     */
    public String getDept() {
        return dept;
    }

    /**
     * 设置部门
     *
     * @param dept 部门
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * 获取部门ID
     *
     * @return DEPT_ID - 部门ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取职位
     *
     * @return POST - 职位
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职位
     *
     * @param post 职位
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取职位ID
     *
     * @return POST_ID - 职位ID
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置职位ID
     *
     * @param postId 职位ID
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * 获取性别
     *
     * @return GENDER - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取负责区域（以“；”拼接）
     *
     * @return AREA - 负责区域（以“；”拼接）
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置负责区域（以“；”拼接）
     *
     * @param area 负责区域（以“；”拼接）
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取负责区域ID（联动末级ID，以“；”拼接）
     *
     * @return AREA_ID - 负责区域ID（联动末级ID，以“；”拼接）
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置负责区域ID（联动末级ID，以“；”拼接）
     *
     * @param areaId 负责区域ID（联动末级ID，以“；”拼接）
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取主账号ID
     *
     * @return MAIN_ID - 主账号ID
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * 设置主账号ID
     *
     * @param mainId 主账号ID
     */
    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    /**
     * 获取备注
     *
     * @return OTHER - 备注
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置备注
     *
     * @param other 备注
     */
    public void setOther(String other) {
        this.other = other;
    }
}