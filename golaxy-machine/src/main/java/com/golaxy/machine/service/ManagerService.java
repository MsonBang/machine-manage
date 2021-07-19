package com.golaxy.machine.service;

import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.common.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 上午9:40
 */
@Service
public interface ManagerService {

    /**
    * @Description: 用户登陆接口
    * @Params: [loginVO]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<com.golaxy.machine.miaoshao.common.entity.UserInfo>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午2:15
    **/
    JsonResult<Map<String, Object>> login(Map<String,Object> map);

    /**
    * @Description: 新增用户接口
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> addUser(Map<String, Object> map);

    /**
    * @Description: 编辑用户接口
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> editUser(UserInfo userInfo);

    /**
    * @Description: 删除用户接口
    * @Params: [id]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> delUser(String id);
}
