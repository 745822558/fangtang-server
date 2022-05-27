package com.lax.ftbot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Account
 * @Description 用户
 * @Author liAnXin
 * @Date 2022/4/27 14:07
 **/
@Data
@AllArgsConstructor
@TableName("jd_cookies")
public class Account implements Serializable {
    /**
     * ID
     */
    @TableField("ID")
    private Integer id;
    /**
     * 优先级
     */
    @TableField("Priority")
    private Integer priority;
    /**
     * 帐号
     */
    @TableField("PtPin")
    private String ptPin;
    /**
     * 临时秘钥
     */
    @TableField("PtKey")
    private String ptKey;
    /**
     * 长期秘钥
     */
    @TableField("WsKey")
    private String wsKey;
    /**
     * 备注
     */
    @TableField("Note")
    private String remark;
    /**
     * 是否有效
     */
    @TableField("Available")
    private String Available;
    /**
     * 昵称
     */
    @TableField("NickName")
    private String nickName;
    /**
     * 一对一推送
     */
    @TableField("PushPlus")
    private String pushPlus;
    /**
     * QQ
     */
    @TableField("QQ")
    private String qq;
}
