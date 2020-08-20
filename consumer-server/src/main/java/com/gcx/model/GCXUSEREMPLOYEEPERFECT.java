package com.gcx.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "GCX_USER_EMPLOYEE_PERFECT")
public class GCXUSEREMPLOYEEPERFECT {
    /**
     * 员工id，关联user id
     */
    @Id
    @Column(name = "EMP_ID")
    private BigDecimal empId;

    /**
     * 姓名
     */
    @Column(name = "EMP_NAME")
    private String empName;

    /**
     * 身份证号
     */
    @Column(name = "ID_NUMBER")
    private String idNumber;

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
     * 性别: 0,男  1,女
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * 现居地
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 现居地ID
     */
    @Column(name = "ADDRESS_ID")
    private String addressId;

    /**
     * 详细住址
     */
    @Column(name = "ADDRESS_DETAIL")
    private String addressDetail;

    /**
     * 身份证正面
     */
    @Column(name = "ID_FORNT")
    private String idFornt;

    /**
     * 身份证反面
     */
    @Column(name = "ID_BACK")
    private String idBack;

    /**
     * 状态：0：未审核 1.通过 2.驳回 3.修改（锁定）4.逻辑占用字段，勿动
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 申请日期
     */
    @Column(name = "START_DATE")
    private String startDate;

    /**
     * 要求完成日期
     */
    @Column(name = "END_DATE")
    private String endDate;

    /**
     * 审核人姓名
     */
    @Column(name = "AUDIT_NAME")
    private String auditName;

    /**
     * 审核时间
     */
    @Column(name = "AUDIT_TIME")
    private String auditTime;

    /**
     * 逻辑删除：0.有效1.删除
     */
    @Column(name = "DELETE_FLAG")
    private Integer deleteFlag;

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

    /**
     * 获取员工id，关联user id
     *
     * @return EMP_ID - 员工id，关联user id
     */
    public BigDecimal getEmpId() {
        return empId;
    }

    /**
     * 设置员工id，关联user id
     *
     * @param empId 员工id，关联user id
     */
    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
    }

    /**
     * 获取姓名
     *
     * @return EMP_NAME - 姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置姓名
     *
     * @param empName 姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取身份证号
     *
     * @return ID_NUMBER - 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号
     *
     * @param idNumber 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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
     * 获取性别: 0,男  1,女
     *
     * @return GENDER - 性别: 0,男  1,女
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别: 0,男  1,女
     *
     * @param gender 性别: 0,男  1,女
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取现居地
     *
     * @return ADDRESS - 现居地
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置现居地
     *
     * @param address 现居地
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取现居地ID
     *
     * @return ADDRESS_ID - 现居地ID
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * 设置现居地ID
     *
     * @param addressId 现居地ID
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取详细住址
     *
     * @return ADDRESS_DETAIL - 详细住址
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * 设置详细住址
     *
     * @param addressDetail 详细住址
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * 获取身份证正面
     *
     * @return ID_FORNT - 身份证正面
     */
    public String getIdFornt() {
        return idFornt;
    }

    /**
     * 设置身份证正面
     *
     * @param idFornt 身份证正面
     */
    public void setIdFornt(String idFornt) {
        this.idFornt = idFornt;
    }

    /**
     * 获取身份证反面
     *
     * @return ID_BACK - 身份证反面
     */
    public String getIdBack() {
        return idBack;
    }

    /**
     * 设置身份证反面
     *
     * @param idBack 身份证反面
     */
    public void setIdBack(String idBack) {
        this.idBack = idBack;
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
     * 获取申请日期
     *
     * @return START_DATE - 申请日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置申请日期
     *
     * @param startDate 申请日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取要求完成日期
     *
     * @return END_DATE - 要求完成日期
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置要求完成日期
     *
     * @param endDate 要求完成日期
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取审核人姓名
     *
     * @return AUDIT_NAME - 审核人姓名
     */
    public String getAuditName() {
        return auditName;
    }

    /**
     * 设置审核人姓名
     *
     * @param auditName 审核人姓名
     */
    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    /**
     * 获取审核时间
     *
     * @return AUDIT_TIME - 审核时间
     */
    public String getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
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
}