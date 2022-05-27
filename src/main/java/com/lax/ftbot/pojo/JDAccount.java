package com.lax.ftbot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName JdAccount
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 14:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_jd_account")
public class JDAccount implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * pt_pin
     */
    private String ptPin;

    /**
     * pt_key
     */
    private String ptKey;

    /**
     * wskey
     */
    private String wskey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效
     */
    private Boolean isValid;

    /**
     * 是否为主账号
     */
    private Boolean isMain;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 一对一推送
     */
    private String pushPlus;
}
