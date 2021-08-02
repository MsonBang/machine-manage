package com.golaxy.machine.service;

import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.common.entity.UserInfo;
import com.golaxy.machine.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 上午9:40
 */
@Service
public interface ManagerService {

    /**
    * @Description: 用户登陆接口定义
    * @Params: [loginVO]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<com.golaxy.machine.miaoshao.common.entity.UserInfo>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午2:15
    **/
    JsonResult<Map<String, Object>> login(Map<String,Object> map);

    /**
    * @Description: 查询用户信息列表接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
    * @Author: miaoxuebing
    * @Date: 2021/7/22 上午11:00
    **/
    JsonResult<PageResult> queryList(Map<String, Object> map);

    /**
    * @Description: 新增用户接口定义
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<UserInfo> addUser(Map<String, Object> map);

    /**
    * @Description: 编辑用户接口定义
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> editUser(Map<String, Object> map);

    /**
    * @Description: 修改用户密码接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/22 下午2:20
    **/
    JsonResult<Integer> editUserPwd(Map<String, Object> map);

    /**
    * @Description: 删除用户接口定义
    * @Params: [id]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:53
    **/
    JsonResult<Integer> delUser(List<String> ids);
}
