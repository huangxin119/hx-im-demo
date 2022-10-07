package org.example.business.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.business.business.mapper.UserApplyMapper;
import org.example.business.business.mapper.UserContactListMapper;
import org.example.business.business.mapper.po.UserApplyPO;
import org.example.business.business.mapper.po.UserContactListPO;
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
    private UserContactListMapper userContactListMapper;
    @Resource
    private MQService mqService;

    @PostMapping("/apply-friend")
    public String applyFriend(@RequestParam Integer userId, @RequestParam Integer receiveId){
        QueryWrapper<UserApplyPO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("receive_id",receiveId);
        List<UserApplyPO> res = userApplyMapper.selectList(queryWrapper);
        if(res!=null&&res.size()>0){
            return "已经存在申请记录，不可重复申请";
        }
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
            UserContactListPO userContactListPO = new UserContactListPO();
            userContactListPO.setUserId(userId);
            userContactListPO.setSessionId(userId+"--"+receiveId);
            userContactListPO.setSessionType(0);
            userContactListMapper.insert(userContactListPO);
            UserContactListPO userContactListPO2 = new UserContactListPO();
            userContactListPO2.setUserId(receiveId);
            userContactListPO2.setSessionId(userId+"--"+receiveId);
            userContactListPO2.setSessionType(0);
            userContactListMapper.insert(userContactListPO2);
            //发送一个初始化聊天栏的业务信息给申请者
            mqService.send("",Business.BusinessMessage.newBuilder().setId("test-init-chat").setUserId(receiveId).setReceiveId(userId).build());
            return "已同意好友申请";
        }else{
            return "已拒绝好友申请";
        }

    }
}
