package com.golaxy.machine.miaoshao.mapper;

import com.golaxy.machine.miaoshao.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 下午2:17
 */
@Mapper
public interface ManagerMapper {

    /**
     * @Description: 根绝用户登录名和用户密码查询当前用户
     * @Params: [loginVO]
     * @Return: com.golaxy.machine.miaoshao.common.entity.UserInfo
     * @Author: miaoxuebing
     * @Date: 2021/1/31 下午2:19
     **/
    UserInfo getUserInfo(String username);
}
