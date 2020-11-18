package com.hrmanager.hr_manager.Model;

import java.util.Date;

public class User {
    public Integer number;

    public String password;

    public Integer roleId;

    public String phone;

    public Date createdate;

    public String username;

    public String remark;

    public Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "number=" + number +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", phone='" + phone + '\'' +
                ", createdate=" + createdate +
                ", username='" + username + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}