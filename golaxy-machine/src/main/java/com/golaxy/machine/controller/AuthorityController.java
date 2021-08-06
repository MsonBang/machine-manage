package com.golaxy.machine.controller;

import com.golaxy.machine.service.RoleService;
import com.golaxy.machine.util.JsonResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[授权校验类，主要针对人员角色分配，授权操作管理]
 * @date 2021/1/31 上午9:22
 */
@RestController
@RequestMapping("/author")
@Api(value = "[用户授权操作和权限查询模块接口]", tags = "[用户授权操作和权限查询模块接口]", description = "[授权管理]")
public class AuthorityController {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
    @Autowired
    private RoleService roleService;

    /**
     * @Description: 为用户授予角色
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:45
     **/
    @PostMapping("/grantRoleToUser")
    @ApiOperation(value = "为用户授予role角色", notes = "为用户授予role角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "主键ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", allowMultiple = true, name = "roleList", value = "角色编码集合", dataType = "String"),
    })
    public JsonResult<Integer> grantRoleToUser(@RequestBody Map<String, Object> map) {
        try {
            return roleService.grantRole(map);
        } catch (Exception e) {
            logger.error("授权异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "授权异常！请联系管理员");
        }
    }


    /**
     * @Description: 给角色分配菜单
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:52
     **/
    @PostMapping("/grantMenuToRole")
    @ApiOperation(value = "为角色分配菜单", notes = "为角色分配菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "rolecode", value = "主键ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", allowMultiple = true, name = "menuList", value = "菜单编码集合", dataType = "String"),
    })
    public JsonResult<Integer> grantMenuToRole(@RequestBody Map<String, Object> map) {
        try {
            return roleService.grantMenu(map);
        } catch (Exception e) {
            logger.error("授权异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "授权异常！请联系管理员");
        }
    }


    /**
     * @Description: 用户登陆获取角色菜单信息
     * @Params: [userId]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:59
     **/
    @GetMapping("/getUserMenuList")
    @ApiOperation(value = "登录后获取当前用户的权限菜单信息", notes = "用户权限菜单获取")
    public JsonResult<Map<String, Object>> getUserMenuList(
            @RequestParam(required = true, defaultValue = "") @ApiParam(required = true, defaultValue = "", value = "用户ID(userId)") String userId) {
        try {
            return roleService.getUserMenuList(userId);
        } catch (Exception e) {
            logger.error("查询异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询异常！请联系管理员");
        }
    }
}
