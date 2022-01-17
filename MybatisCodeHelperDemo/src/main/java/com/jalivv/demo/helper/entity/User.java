package com.jalivv.demo.helper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description ${DESCRIPTION}
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "userName";
    public static final String COL_PASSWORD = "password";
    public static final String COL_TRUENAME = "trueName";
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "userName")
    private String username;
    @TableField(value = "`password`")
    private String password;
    @TableField(value = "trueName")
    private String truename;
}
