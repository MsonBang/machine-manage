package com.golaxy.machine.miaoshao.manager.service;

import com.golaxy.machine.miaoshao.common.entity.JsonResult;
import com.golaxy.machine.miaoshao.common.entity.UserInfo;
import com.golaxy.machine.miaoshao.manager.vo.LoginVO;
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
    JsonResult<Map<String, Object>> login(LoginVO loginVO);

    /**
    * @Description: 新增用户接口
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> addUser(UserInfo userInfo);

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
