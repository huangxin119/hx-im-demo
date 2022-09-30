package org.example.server.server.mysql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.22
 */
@Data
@TableName("user_account")
public class UserAccount {
    private Integer id;
    private Integer userId;
    private String userName;
    private String password;
    private Integer validStatus;
    private Date createTime;
    private Date updateTime;
}
