package com.lax.ftbot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserAccount
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 14:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_account")
public class UserAccount implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wx;
}
