package org.example.business.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.business.business.mapper.UserApplyMapper;
import org.example.business.business.mapper.po.UserApplyPO;
import org.example.business.business.mq.MQService;
import org.example.common.po.Business;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @desc： 联系人
 * @author: huangxin
 * @date: 2022.09.06
 */
@RestController
@RequestMapping("/user-contract")
public class UserContactController {
    @Resource
    private UserApplyMapper userApplyMapper;
    @Resource
    private MQService mqService;

    @PostMapping("/apply-friend")
    public String applyFriend(@RequestParam Integer userId, @RequestParam Integer receiveId){
        UserApplyPO userApplyPO = new UserApplyPO();
        userApplyPO.setUserId(userId);
        userApplyPO.setReceiveId(receiveId);
        userApplyPO.setApplyStatus(0);
        userApplyPO.setApplyType(0);
        userApplyMapper.insert(userApplyPO);
        return "好友申请已发送";
    }

    @GetMapping("/get-friend-apply")
    public List<Integer> getFriendApply(@RequestParam Integer userId){
        QueryWrapper<UserApplyPO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("receive_id",userId);
        List<UserApplyPO> res = userApplyMapper.selectList(queryWrapper);
        return res.stream().map(u -> u.getUserId()).collect(Collectors.toList());
    }

    @PostMapping("/deal-apply-result")
    public String dealApplyResult(@RequestParam Integer userId,@RequestParam Integer receiveId,@RequestParam Integer dealType){
        UpdateWrapper<UserApplyPO> updateWrapper = new UpdateWrapper();
        UserApplyPO userApplyPO = new UserApplyPO();
        userApplyPO.setApplyStatus(dealType);
        updateWrapper.eq("user_id",userId);
        updateWrapper.eq("receive_id",receiveId);
        userApplyMapper.update(userApplyPO,updateWrapper);
        if(dealType==1){
            //发送一个初始化聊天栏的业务信息给申请者
            mqService.send("",Business.BusinessMessage.newBuilder().setId("test-init-chat").setUserId(receiveId).setReceiveId(userId).build());
            return "已同意好友申请";
        }else{
            return "已拒绝好友申请";
        }

    }
}
