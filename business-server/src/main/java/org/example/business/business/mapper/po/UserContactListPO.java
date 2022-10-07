package org.example.business.business.mapper.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String sessionId;
    private Integer sessionType;
    private Integer unReadNum;
    private Integer lastMessageId;
    private Integer validStatus;
    private Date createTime;
}
