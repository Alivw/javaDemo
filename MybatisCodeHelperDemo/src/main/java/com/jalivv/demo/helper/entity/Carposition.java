package com.jalivv.demo.helper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description ${DESCRIPTION}
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_carposition")
public class Carposition implements Serializable {
    public static final String COL_PID = "pid";
    public static final String COL_AREAID = "areaId";
    public static final String COL_POSITIONNO = "positionNo";
    public static final String COL_ADDTIME = "addTime";
    public static final String COL_ISACTIVE = "isactive";
    private static final long serialVersionUID = 1L;
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;
    @TableField(value = "areaId")
    private Integer areaid;
    @TableField(value = "positionNo")
    private String positionno;
    @TableField(value = "addTime")
    private Date addtime;
    @TableField(value = "isactive")
    private Integer isactive;
}
