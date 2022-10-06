package org.example.business.business.mapper.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer receiveId;
    private Integer applyType;
    private Integer applyStatus;
    private Date createTime;
}
