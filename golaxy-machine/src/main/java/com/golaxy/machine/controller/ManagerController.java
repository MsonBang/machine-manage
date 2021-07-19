package com.golaxy.machine.controller;

import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.service.ManagerService;
import com.golaxy.machine.common.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: 用户管理员模块
 * @date 2021/1/31 上午9:22
 */
@RestController
@RequestMapping("/manager")
@Api(value = "[管理员用户管理模块接口]", tags = "[管理员用户管理模块接口]", description = "[管理员用户管理]")
public class ManagerController {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    private ManagerService managerService;


    /**
    * @Description: 用户管理员登录接口
    * @Params: [loginVO]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<com.golaxy.machine.miaoshao.common.entity.UserInfo>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 上午10:48
    **/
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户登陆名",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="password",value="用户密码",dataType="String", paramType = "query")
    })
    @PostMapping("/login")
    public JsonResult<Map<String, Object>> login(@RequestBody Map<String, Object> map) {
        try {
            //查询字段转化为map集合
            return managerService.login(map);
        } catch (Exception e) {
            logger.error("登录异常！请联系管理员", e.getMessage());
            return new JsonResult<>(JsonResult.FAIL, "登录异常！请联系管理员");
        }
    }

    /**
    * @Description: 用户添加接口
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:42
    **/
    @PostMapping("/add")
    @ApiOperation(value = "新增用户接口", notes = "新增用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户登录名", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "用户密码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "repassword", value = "用户确认密码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "用户姓名", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "createuser", value = "创建人", dataType = "String")
    })
    public JsonResult<Integer> addUser(@RequestBody Map<String, Object> map) {
        try {
            //查询字段转化为map集合
            return managerService.addUser(map);
        } catch (Exception e) {
            logger.error("添加异常！请联系管理员", e.getMessage());
            return new JsonResult<>(JsonResult.FAIL, "添加异常！请联系管理员");
        }
    }

    /**
    * @Description: 编辑用户接口
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:44
    **/
    @PostMapping("/edit")
    @ApiOperation(value = "修改用户接口", notes = "修改用户接口")
    public JsonResult<Integer> editUser(@RequestBody UserInfo userInfo){
        try {
            //查询字段转化为map集合
            return managerService.editUser(userInfo);
        } catch (Exception e) {
            logger.error("修改异常！请联系管理员", e.getMessage());
            return new JsonResult<>(JsonResult.FAIL,"修改异常！请联系管理员");
        }
    }

    /**
    * @Description: 删除用户接口
    * @Params: []
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:46
    **/
    @PostMapping("/del")
    @ApiOperation(value = "删除用户接口", notes = "删除用户接口")
    public JsonResult<Integer> delUser(@PathVariable String id){
        try {
            //查询字段转化为map集合
            return managerService.delUser(id);
        } catch (Exception e) {
            logger.error("删除异常！请联系管理员", e.getMessage());
            return new JsonResult<>(JsonResult.FAIL,"删除异常！请联系管理员");
        }
    }

}
