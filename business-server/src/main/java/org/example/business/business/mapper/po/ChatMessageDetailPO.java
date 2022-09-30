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
@TableName("chat_message_detail")
public class ChatMessageDetailPO {
    private Integer id;
    private String messageId;
    private String sessionId;
    private Integer userId;
    private Integer receiveId;
    private Integer status;
    private String content;
    private Integer isRead;
    private Date createTime;
}
