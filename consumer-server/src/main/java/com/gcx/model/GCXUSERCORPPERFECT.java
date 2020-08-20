package com.gcx.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "GCX_USER_CORP_PERFECT")
public class GCXUSERCORPPERFECT {
    /**
     * 企业id，关联user id
     */
    @Id
    @Column(name = "CORP_ID")
    private BigDecimal corpId;

    /**
     * 授权联系人
     */
    @Column(name = "CORP_PEOPLE")
    private String corpPeople;

    /**
     * 手机号
     */
    @Column(name = "PHONENUM")
    private String phonenum;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 性别0.男1.女
     */
    @Column(name = "GENDER")
    private Short gender;

    /**
     * 企业名称
     */
    @Column(name = "CORP_NAME")
    private String corpName;

    /**
     * 企业地址：“,”拼接
     */
    @Column(name = "CORP_ADDRESS")
    private String corpAddress;

    /**
     * 企业地址ip：“:”拼接
     */
    @Column(name = "CORP_ADDRESS_ID")
    private String corpAddressId;

    /**
     * 企业详细地址
     */
    @Column(name = "CORP_DETAIL_ADDRESS")
    private String corpDetailAddress;

    /**
     * 公司所属行业
     */
    @Column(name = "CORP_INDUSTRY")
    private String corpIndustry;

    /**
     * 统一社会信用代码或工商注册号
     */
    @Column(name = "CERTIFI_NUM")
    private String certifiNum;

    /**
     * 法人姓名
     */
    @Column(name = "FRNAME")
    private String frname;

    /**
     * 法人身份证号
     */
    @Column(name = "FRTEL")
    private String frtel;

    /**
     * 营业执照照片
     */
    @Column(name = "BUSINESS_LICENCE")
    private String businessLicence;

    /**
     * 企业授权书照片
     */
    @Column(name = "CERTIFICATE")
    private String certificate;

    /**
     * 法人身份证图片地址
     */
    @Column(name = "ID_PICTURE")
    private String idPicture;

    /**
     * 状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 逻辑删除：0.有效1.删除
     */
    @Column(name = "DELETE_FLAG")
    private Integer deleteFlag;

    /**
     * 申请时间
     */
    private Date inserttime;

    /**
     * 国家
     */
    @Column(name = "A1")
    private String a1;

    /**
     * 国家ID
     */
    @Column(name = "A2")
    private String a2;

    /**
     * 申请日期
     */
    @Column(name = "A3")
    private String a3;

    /**
     * 要求完成日期
     */
    @Column(name = "A4")
    private String a4;

    /**
     * 法人名称，身份证是否符合
     */
    @Column(name = "A5")
    private String a5;

    /**
     * 文字信息检查是否符合
     */
    @Column(name = "A6")
    private String a6;

    /**
     * 结论
     */
    @Column(name = "A7")
    private String a7;

    /**
     * 审核人员
     */
    @Column(name = "A8")
    private String a8;

    /**
     * 审核日期
     */
    @Column(name = "A9")
    private String a9;

    /**
     * 类型 ：1.企业 9.协会 13.园区
     */
    @Column(name = "A10")
    private String a10;

    /**
     * 企业logo
     */
    @Column(name = "CORP_LOGO")
    private String corpLogo;

    /**
     * 实名认证 0.已认证  1.未认证
     */
    @Column(name = "VERIFIED")
    private String verified;

    /**
     * 企业名片
     */
    @Column(name = "CARD")
    private String card;

    /**
     * 信用报告名片
     */
    @Column(name = "CREDIT_CARD")
    private String creditCard;

    /**
     * 企业简介
     */
    @Column(name = "INTRODUCTION")
    private String introduction;

    /**
     * 企业项目类别
     */
    @Column(name = "QYXMLB")
    private String qyxmlb;

    /**
     * 企业类型
     */
    @Column(name = "QYLX")
    private String qylx;

    /**
     * 资金类型
     */
    @Column(name = "ZJLX")
    private String zjlx;

    /**
     * 注册资金
     */
    @Column(name = "ZCZJ")
    private String zczj;

    /**
     * 人员规模
     */
    @Column(name = "RYGM")
    private String rygm;

    /**
     * 营业期限（开始日期）
     */
    @Column(name = "YYQX")
    private Date yyqx;

    /**
     * 营业期限（结束日期）
     */
    @Column(name = "YYQX2")
    private Date yyqx2;

    /**
     * 企业网址
     */
    @Column(name = "QYWZ")
    private String qywz;

    /**
     * 销售人员账户
     */
    @Column(name = "Z1")
    private String z1;

    @Column(name = "Z2")
    private String z2;

    @Column(name = "Z3")
    private String z3;

    @Column(name = "Z4")
    private String z4;

    @Column(name = "Z5")
    private String z5;

    @Column(name = "Z6")
    private String z6;

    @Column(name = "Z7")
    private String z7;

    /**
     * 临时用户名
     */
    @Column(name = "Z8")
    private String z8;

    /**
     * 临时密码
     */
    @Column(name = "Z9")
    private String z9;

    @Column(name = "Y1")
    private String y1;

    @Column(name = "Y2")
    private String y2;

    @Column(name = "Y3")
    private String y3;

    @Column(name = "Y4")
    private String y4;

    @Column(name = "Y5")
    private String y5;

    @Column(name = "Y6")
    private String y6;

    @Column(name = "Y7")
    private String y7;

    @Column(name = "Y8")
    private String y8;

    @Column(name = "Y9")
    private String y9;

    /**
     * 获取企业id，关联user id
     *
     * @return CORP_ID - 企业id，关联user id
     */
    public BigDecimal getCorpId() {
        return corpId;
    }

    /**
     * 设置企业id，关联user id
     *
     * @param corpId 企业id，关联user id
     */
    public void setCorpId(BigDecimal corpId) {
        this.corpId = corpId;
    }

    /**
     * 获取授权联系人
     *
     * @return CORP_PEOPLE - 授权联系人
     */
    public String getCorpPeople() {
        return corpPeople;
    }

    /**
     * 设置授权联系人
     *
     * @param corpPeople 授权联系人
     */
    public void setCorpPeople(String corpPeople) {
        this.corpPeople = corpPeople;
    }

    /**
     * 获取手机号
     *
     * @return PHONENUM - 手机号
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * 设置手机号
     *
     * @param phonenum 手机号
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
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
     * 获取性别0.男1.女
     *
     * @return GENDER - 性别0.男1.女
     */
    public Short getGender() {
        return gender;
    }

    /**
     * 设置性别0.男1.女
     *
     * @param gender 性别0.男1.女
     */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * 获取企业名称
     *
     * @return CORP_NAME - 企业名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 设置企业名称
     *
     * @param corpName 企业名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 获取企业地址：“,”拼接
     *
     * @return CORP_ADDRESS - 企业地址：“,”拼接
     */
    public String getCorpAddress() {
        return corpAddress;
    }

    /**
     * 设置企业地址：“,”拼接
     *
     * @param corpAddress 企业地址：“,”拼接
     */
    public void setCorpAddress(String corpAddress) {
        this.corpAddress = corpAddress;
    }

    /**
     * 获取企业地址ip：“:”拼接
     *
     * @return CORP_ADDRESS_ID - 企业地址ip：“:”拼接
     */
    public String getCorpAddressId() {
        return corpAddressId;
    }

    /**
     * 设置企业地址ip：“:”拼接
     *
     * @param corpAddressId 企业地址ip：“:”拼接
     */
    public void setCorpAddressId(String corpAddressId) {
        this.corpAddressId = corpAddressId;
    }

    /**
     * 获取企业详细地址
     *
     * @return CORP_DETAIL_ADDRESS - 企业详细地址
     */
    public String getCorpDetailAddress() {
        return corpDetailAddress;
    }

    /**
     * 设置企业详细地址
     *
     * @param corpDetailAddress 企业详细地址
     */
    public void setCorpDetailAddress(String corpDetailAddress) {
        this.corpDetailAddress = corpDetailAddress;
    }

    /**
     * 获取公司所属行业
     *
     * @return CORP_INDUSTRY - 公司所属行业
     */
    public String getCorpIndustry() {
        return corpIndustry;
    }

    /**
     * 设置公司所属行业
     *
     * @param corpIndustry 公司所属行业
     */
    public void setCorpIndustry(String corpIndustry) {
        this.corpIndustry = corpIndustry;
    }

    /**
     * 获取统一社会信用代码或工商注册号
     *
     * @return CERTIFI_NUM - 统一社会信用代码或工商注册号
     */
    public String getCertifiNum() {
        return certifiNum;
    }

    /**
     * 设置统一社会信用代码或工商注册号
     *
     * @param certifiNum 统一社会信用代码或工商注册号
     */
    public void setCertifiNum(String certifiNum) {
        this.certifiNum = certifiNum;
    }

    /**
     * 获取法人姓名
     *
     * @return FRNAME - 法人姓名
     */
    public String getFrname() {
        return frname;
    }

    /**
     * 设置法人姓名
     *
     * @param frname 法人姓名
     */
    public void setFrname(String frname) {
        this.frname = frname;
    }

    /**
     * 获取法人身份证号
     *
     * @return FRTEL - 法人身份证号
     */
    public String getFrtel() {
        return frtel;
    }

    /**
     * 设置法人身份证号
     *
     * @param frtel 法人身份证号
     */
    public void setFrtel(String frtel) {
        this.frtel = frtel;
    }

    /**
     * 获取营业执照照片
     *
     * @return BUSINESS_LICENCE - 营业执照照片
     */
    public String getBusinessLicence() {
        return businessLicence;
    }

    /**
     * 设置营业执照照片
     *
     * @param businessLicence 营业执照照片
     */
    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    /**
     * 获取企业授权书照片
     *
     * @return CERTIFICATE - 企业授权书照片
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * 设置企业授权书照片
     *
     * @param certificate 企业授权书照片
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    /**
     * 获取法人身份证图片地址
     *
     * @return ID_PICTURE - 法人身份证图片地址
     */
    public String getIdPicture() {
        return idPicture;
    }

    /**
     * 设置法人身份证图片地址
     *
     * @param idPicture 法人身份证图片地址
     */
    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture;
    }

    /**
     * 获取状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     *
     * @return STATUS - 状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     *
     * @param status 状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 获取申请时间
     *
     * @return inserttime - 申请时间
     */
    public Date getInserttime() {
        return inserttime;
    }

    /**
     * 设置申请时间
     *
     * @param inserttime 申请时间
     */
    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    /**
     * 获取国家
     *
     * @return A1 - 国家
     */
    public String getA1() {
        return a1;
    }

    /**
     * 设置国家
     *
     * @param a1 国家
     */
    public void setA1(String a1) {
        this.a1 = a1;
    }

    /**
     * 获取国家ID
     *
     * @return A2 - 国家ID
     */
    public String getA2() {
        return a2;
    }

    /**
     * 设置国家ID
     *
     * @param a2 国家ID
     */
    public void setA2(String a2) {
        this.a2 = a2;
    }

    /**
     * 获取申请日期
     *
     * @return A3 - 申请日期
     */
    public String getA3() {
        return a3;
    }

    /**
     * 设置申请日期
     *
     * @param a3 申请日期
     */
    public void setA3(String a3) {
        this.a3 = a3;
    }

    /**
     * 获取要求完成日期
     *
     * @return A4 - 要求完成日期
     */
    public String getA4() {
        return a4;
    }

    /**
     * 设置要求完成日期
     *
     * @param a4 要求完成日期
     */
    public void setA4(String a4) {
        this.a4 = a4;
    }

    /**
     * 获取法人名称，身份证是否符合
     *
     * @return A5 - 法人名称，身份证是否符合
     */
    public String getA5() {
        return a5;
    }

    /**
     * 设置法人名称，身份证是否符合
     *
     * @param a5 法人名称，身份证是否符合
     */
    public void setA5(String a5) {
        this.a5 = a5;
    }

    /**
     * 获取文字信息检查是否符合
     *
     * @return A6 - 文字信息检查是否符合
     */
    public String getA6() {
        return a6;
    }

    /**
     * 设置文字信息检查是否符合
     *
     * @param a6 文字信息检查是否符合
     */
    public void setA6(String a6) {
        this.a6 = a6;
    }

    /**
     * 获取结论
     *
     * @return A7 - 结论
     */
    public String getA7() {
        return a7;
    }

    /**
     * 设置结论
     *
     * @param a7 结论
     */
    public void setA7(String a7) {
        this.a7 = a7;
    }

    /**
     * 获取审核人员
     *
     * @return A8 - 审核人员
     */
    public String getA8() {
        return a8;
    }

    /**
     * 设置审核人员
     *
     * @param a8 审核人员
     */
    public void setA8(String a8) {
        this.a8 = a8;
    }

    /**
     * 获取审核日期
     *
     * @return A9 - 审核日期
     */
    public String getA9() {
        return a9;
    }

    /**
     * 设置审核日期
     *
     * @param a9 审核日期
     */
    public void setA9(String a9) {
        this.a9 = a9;
    }

    /**
     * 获取类型 ：1.企业 9.协会 13.园区
     *
     * @return A10 - 类型 ：1.企业 9.协会 13.园区
     */
    public String getA10() {
        return a10;
    }

    /**
     * 设置类型 ：1.企业 9.协会 13.园区
     *
     * @param a10 类型 ：1.企业 9.协会 13.园区
     */
    public void setA10(String a10) {
        this.a10 = a10;
    }

    /**
     * 获取企业logo
     *
     * @return CORP_LOGO - 企业logo
     */
    public String getCorpLogo() {
        return corpLogo;
    }

    /**
     * 设置企业logo
     *
     * @param corpLogo 企业logo
     */
    public void setCorpLogo(String corpLogo) {
        this.corpLogo = corpLogo;
    }

    /**
     * 获取实名认证 0.已认证  1.未认证
     *
     * @return VERIFIED - 实名认证 0.已认证  1.未认证
     */
    public String getVerified() {
        return verified;
    }

    /**
     * 设置实名认证 0.已认证  1.未认证
     *
     * @param verified 实名认证 0.已认证  1.未认证
     */
    public void setVerified(String verified) {
        this.verified = verified;
    }

    /**
     * 获取企业名片
     *
     * @return CARD - 企业名片
     */
    public String getCard() {
        return card;
    }

    /**
     * 设置企业名片
     *
     * @param card 企业名片
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * 获取信用报告名片
     *
     * @return CREDIT_CARD - 信用报告名片
     */
    public String getCreditCard() {
        return creditCard;
    }

    /**
     * 设置信用报告名片
     *
     * @param creditCard 信用报告名片
     */
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * 获取企业简介
     *
     * @return INTRODUCTION - 企业简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置企业简介
     *
     * @param introduction 企业简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取企业项目类别
     *
     * @return QYXMLB - 企业项目类别
     */
    public String getQyxmlb() {
        return qyxmlb;
    }

    /**
     * 设置企业项目类别
     *
     * @param qyxmlb 企业项目类别
     */
    public void setQyxmlb(String qyxmlb) {
        this.qyxmlb = qyxmlb;
    }

    /**
     * 获取企业类型
     *
     * @return QYLX - 企业类型
     */
    public String getQylx() {
        return qylx;
    }

    /**
     * 设置企业类型
     *
     * @param qylx 企业类型
     */
    public void setQylx(String qylx) {
        this.qylx = qylx;
    }

    /**
     * 获取资金类型
     *
     * @return ZJLX - 资金类型
     */
    public String getZjlx() {
        return zjlx;
    }

    /**
     * 设置资金类型
     *
     * @param zjlx 资金类型
     */
    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    /**
     * 获取注册资金
     *
     * @return ZCZJ - 注册资金
     */
    public String getZczj() {
        return zczj;
    }

    /**
     * 设置注册资金
     *
     * @param zczj 注册资金
     */
    public void setZczj(String zczj) {
        this.zczj = zczj;
    }

    /**
     * 获取人员规模
     *
     * @return RYGM - 人员规模
     */
    public String getRygm() {
        return rygm;
    }

    /**
     * 设置人员规模
     *
     * @param rygm 人员规模
     */
    public void setRygm(String rygm) {
        this.rygm = rygm;
    }

    /**
     * 获取营业期限（开始日期）
     *
     * @return YYQX - 营业期限（开始日期）
     */
    public Date getYyqx() {
        return yyqx;
    }

    /**
     * 设置营业期限（开始日期）
     *
     * @param yyqx 营业期限（开始日期）
     */
    public void setYyqx(Date yyqx) {
        this.yyqx = yyqx;
    }

    /**
     * 获取营业期限（结束日期）
     *
     * @return YYQX2 - 营业期限（结束日期）
     */
    public Date getYyqx2() {
        return yyqx2;
    }

    /**
     * 设置营业期限（结束日期）
     *
     * @param yyqx2 营业期限（结束日期）
     */
    public void setYyqx2(Date yyqx2) {
        this.yyqx2 = yyqx2;
    }

    /**
     * 获取企业网址
     *
     * @return QYWZ - 企业网址
     */
    public String getQywz() {
        return qywz;
    }

    /**
     * 设置企业网址
     *
     * @param qywz 企业网址
     */
    public void setQywz(String qywz) {
        this.qywz = qywz;
    }

    /**
     * 获取销售人员账户
     *
     * @return Z1 - 销售人员账户
     */
    public String getZ1() {
        return z1;
    }

    /**
     * 设置销售人员账户
     *
     * @param z1 销售人员账户
     */
    public void setZ1(String z1) {
        this.z1 = z1;
    }

    /**
     * @return Z2
     */
    public String getZ2() {
        return z2;
    }

    /**
     * @param z2
     */
    public void setZ2(String z2) {
        this.z2 = z2;
    }

    /**
     * @return Z3
     */
    public String getZ3() {
        return z3;
    }

    /**
     * @param z3
     */
    public void setZ3(String z3) {
        this.z3 = z3;
    }

    /**
     * @return Z4
     */
    public String getZ4() {
        return z4;
    }

    /**
     * @param z4
     */
    public void setZ4(String z4) {
        this.z4 = z4;
    }

    /**
     * @return Z5
     */
    public String getZ5() {
        return z5;
    }

    /**
     * @param z5
     */
    public void setZ5(String z5) {
        this.z5 = z5;
    }

    /**
     * @return Z6
     */
    public String getZ6() {
        return z6;
    }

    /**
     * @param z6
     */
    public void setZ6(String z6) {
        this.z6 = z6;
    }

    /**
     * @return Z7
     */
    public String getZ7() {
        return z7;
    }

    /**
     * @param z7
     */
    public void setZ7(String z7) {
        this.z7 = z7;
    }

    /**
     * 获取临时用户名
     *
     * @return Z8 - 临时用户名
     */
    public String getZ8() {
        return z8;
    }

    /**
     * 设置临时用户名
     *
     * @param z8 临时用户名
     */
    public void setZ8(String z8) {
        this.z8 = z8;
    }

    /**
     * 获取临时密码
     *
     * @return Z9 - 临时密码
     */
    public String getZ9() {
        return z9;
    }

    /**
     * 设置临时密码
     *
     * @param z9 临时密码
     */
    public void setZ9(String z9) {
        this.z9 = z9;
    }

    /**
     * @return Y1
     */
    public String getY1() {
        return y1;
    }

    /**
     * @param y1
     */
    public void setY1(String y1) {
        this.y1 = y1;
    }

    /**
     * @return Y2
     */
    public String getY2() {
        return y2;
    }

    /**
     * @param y2
     */
    public void setY2(String y2) {
        this.y2 = y2;
    }

    /**
     * @return Y3
     */
    public String getY3() {
        return y3;
    }

    /**
     * @param y3
     */
    public void setY3(String y3) {
        this.y3 = y3;
    }

    /**
     * @return Y4
     */
    public String getY4() {
        return y4;
    }

    /**
     * @param y4
     */
    public void setY4(String y4) {
        this.y4 = y4;
    }

    /**
     * @return Y5
     */
    public String getY5() {
        return y5;
    }

    /**
     * @param y5
     */
    public void setY5(String y5) {
        this.y5 = y5;
    }

    /**
     * @return Y6
     */
    public String getY6() {
        return y6;
    }

    /**
     * @param y6
     */
    public void setY6(String y6) {
        this.y6 = y6;
    }

    /**
     * @return Y7
     */
    public String getY7() {
        return y7;
    }

    /**
     * @param y7
     */
    public void setY7(String y7) {
        this.y7 = y7;
    }

    /**
     * @return Y8
     */
    public String getY8() {
        return y8;
    }

    /**
     * @param y8
     */
    public void setY8(String y8) {
        this.y8 = y8;
    }

    /**
     * @return Y9
     */
    public String getY9() {
        return y9;
    }

    /**
     * @param y9
     */
    public void setY9(String y9) {
        this.y9 = y9;
    }
}