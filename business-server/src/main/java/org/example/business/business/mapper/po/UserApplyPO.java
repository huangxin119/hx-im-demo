package org.example.business.business.mapper.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.09.06
 */
@Data
@TableName("user_apply")
public class UserApplyPO {
    private Integer id;
    private Integer userId;
    private Integer receiveId;
    private Integer applyType;
    private Integer applyStatus;
    private Date createTime;
}
