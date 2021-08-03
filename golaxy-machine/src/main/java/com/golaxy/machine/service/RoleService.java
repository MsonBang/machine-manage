package com.golaxy.machine.service;

import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[角色信息接口定义类]
 * @date 2021/8/3 下午2:52
 */
@Service
public interface RoleService {

    /**
     * @Description: 角色列表条件查询接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.common.entity.RoleInfo>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:23
     **/
    JsonResult<PageResult> queryList(Map<String, Object> map);

    /**
     * @Description: 新增角色接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午2:58
     **/
    JsonResult<Integer> addRole(Map<String, Object> map);

    /**
     * @Description: 编辑角色接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:16
     **/
    JsonResult<Integer> editRole(Map<String, Object> map);

    /**
     * @Description: 删除角色接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:35
     **/
    JsonResult<Boolean> delRole(String roleId);

    /**
     * @Description: 给用户授予角色权限接口定义
     * @Params: [userId, roleList]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:40
     **/
    JsonResult<Integer> grantRole(Map<String, Object> map);

    /**
     * @Description: 给角色分配菜单接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:51
     **/
    JsonResult<Integer> grantMenu(Map<String, Object> map);

    /**
     * @Description: 用户登陆查询角色菜单接口定义
     * @Params: [userId]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:59
     **/
    JsonResult<Map<String, Object>> getUserMenuList(String userId);
}
