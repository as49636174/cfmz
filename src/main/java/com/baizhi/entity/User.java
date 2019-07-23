package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @Excel(name = "编号",isColumnHidden = true)
    private String id;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "盐",isColumnHidden = true)
    private String salt;
    @Excel(name = "法名")
    private String dharma;
    @Excel(name = "省")
    private String province;
    @Excel(name = "市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "头像",type = 1)
    private String photo;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "创建时间")
    private Date create_date;
}
