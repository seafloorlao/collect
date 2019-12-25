package com.malt.collect.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer id;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 微信
     */
    private String weChat;
    /**
     * 申请时间
     */
    private LocalDateTime gmtCreate;
}
