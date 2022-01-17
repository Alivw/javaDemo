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
@TableName(value = "t_carstop")
public class Carstop implements Serializable {
    public static final String COL_CID = "cid";
    public static final String COL_CARNUMBER = "carNumber";
    public static final String COL_CARTYPEID = "carTypeId";
    public static final String COL_CARPOSITIONNO = "carPositionNo";
    public static final String COL_STARTTIME = "startTime";
    public static final String COL_ENDTIME = "endTime";
    public static final String COL_STOPDURATION = "stopDuration";
    public static final String COL_STOPCOAT = "stopCoat";
    public static final String COL_PRICE = "price";
    public static final String COL_PID = "pid";
    private static final long serialVersionUID = 1L;
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    @TableField(value = "carNumber")
    private String carnumber;
    @TableField(value = "carTypeId")
    private Integer cartypeid;
    @TableField(value = "carPositionNo")
    private String carpositionno;
    @TableField(value = "startTime")
    private Date starttime;
    @TableField(value = "endTime")
    private Date endtime;
    @TableField(value = "stopDuration")
    private Double stopduration;
    @TableField(value = "stopCoat")
    private Double stopcoat;
    @TableField(value = "price")
    private Double price;
    @TableField(value = "pid")
    private Integer pid;
}
