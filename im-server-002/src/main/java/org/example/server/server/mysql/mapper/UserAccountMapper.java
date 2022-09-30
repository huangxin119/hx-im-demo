package org.example.server.server.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.server.server.mysql.UserAccount;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.22
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    @Select("select * from user_account where userName = #{userName} and password = #{password} and validStatus = 1 limit 1")
    UserAccount selectValidAccount(@Param("userName") String userName, @Param("password") String password);
}
