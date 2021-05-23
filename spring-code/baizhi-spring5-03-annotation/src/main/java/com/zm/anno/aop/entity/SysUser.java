package com.zm.anno.aop.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2021-04-11 20:13:39
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 451113732375202446L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 登录账号
    */
    private String username;
    /**
    * 真实姓名
    */
    private String realname;
    /**
    * 密码
    */
    private String password;
    /**
    * md5密码盐
    */
    private String salt;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 生日
    */
    private Date birthday;
    /**
    * 性别(0-默认未知,1-男,2-女)
    */
    private Object sex;
    /**
    * 电子邮件
    */
    private String email;
    /**
    * 电话
    */
    private String phone;
    /**
    * 机构编码
    */
    private String orgCode;
    /**
    * 性别(1-正常,2-冻结)
    */
    private Object status;
    /**
    * 删除状态(0-正常,1-已删除)
    */
    private Object delFlag;
    /**
    * 第三方登录的唯一标识
    */
    private String thirdId;
    /**
    * 第三方类型
    */
    private String thirdType;
    /**
    * 同步工作流引擎(1-同步,0-不同步)
    */
    private Object activitiSync;
    /**
    * 工号，唯一键
    */
    private String workNo;
    /**
    * 职务，关联职务表
    */
    private String post;
    /**
    * 座机号
    */
    private String telephone;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新人
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 身份（1普通成员 2上级）
    */
    private Object userIdentity;
    /**
    * 负责部门
    */
    private Object departIds;
    /**
    * 多租户标识
    */
    private String relTenantIds;
    /**
    * 设备ID
    */
    private String clientId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Object getActivitiSync() {
        return activitiSync;
    }

    public void setActivitiSync(Object activitiSync) {
        this.activitiSync = activitiSync;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Object userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Object getDepartIds() {
        return departIds;
    }

    public void setDepartIds(Object departIds) {
        this.departIds = departIds;
    }

    public String getRelTenantIds() {
        return relTenantIds;
    }

    public void setRelTenantIds(String relTenantIds) {
        this.relTenantIds = relTenantIds;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}