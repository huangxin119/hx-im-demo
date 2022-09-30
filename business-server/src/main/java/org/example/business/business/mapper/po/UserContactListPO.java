package org.example.business.business.mapper.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.09.07
 */
@Data
@TableName("user_contact_list")
public class UserContactListPO {
    private Integer id;
    private Integer userId;
    private Integer sessionId;
    private Integer sessionType;
    private Integer unReadNum;
    private Integer lastMessageId;
    private Integer validStatus;
    private Date createTime;
}
