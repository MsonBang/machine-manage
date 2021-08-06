package com.golaxy.machine.controller;

import com.golaxy.machine.service.RoleService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[系统角色类，针对系统用户所有角色进行管理操作]
 * @date 2021/7/16 下午4:08
 */

@RestController
@RequestMapping("/role")
@Api(value = "[角色管理模块接口]", tags = "[角色管理模块接口]", description = "[角色管理]")
public class RoleController {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;


    /**
     * @Description: 查询角色列表
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.common.entity.RoleInfo>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:20
     **/
    @PostMapping("/list")
    @ApiOperation(value = "条件查询角色列表接口", notes = "条件查询角色列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "rolename", value = "角色名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolecode", value = "角色编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolestatus", value = "角色开启状态", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", dataType = "int")
    })
    public JsonResult<PageResult> queryRoleList(@RequestBody Map<String, Object> map) {
        try {
            return roleService.queryList(map);
        } catch (Exception e) {
            logger.error("查询异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询异常！请联系管理员");
        }
    }


    /**
     * @Description: 新增角色信息
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:18
     **/
    @PostMapping("/add")
    @ApiOperation(value = "新增角色信息接口", notes = "新增角色信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "rolename", value = "角色名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolecode", value = "角色编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolestatus", value = "角色开启状态", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "roleorder", value = "角色排序", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "角色描述信息", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "createuser", value = "创建人", dataType = "string")
    })
    public JsonResult<Integer> addRole(@RequestBody Map<String, Object> map) {
        try {
            return roleService.addRole(map);
        } catch (Exception e) {
            logger.error("添加异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "添加异常！请联系管理员");
        }
    }


    /**
     * @Description: 修改角色信息
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:18
     **/
    @PostMapping("/edit")
    @ApiOperation(value = "修改角色信息接口", notes = "修改角色信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolename", value = "角色名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolecode", value = "角色编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "rolestatus", value = "角色开启状态", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "roleorder", value = "角色排序", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "角色描述信息", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "updateuser", value = "创建人", dataType = "string")
    })
    public JsonResult<Integer> editRole(@RequestBody Map<String, Object> map) {
        try {
            return roleService.editRole(map);
        } catch (Exception e) {
            logger.error("修改异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }


    /**
     * @Description: 删除角色信息
     * @Params: [roleId]
     * @Return: Response
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午3:33
     **/
    @GetMapping("/del")
    @ApiOperation(value = "删除角色接口", notes = "删除角色接口")
    public JsonResult<Boolean> delUser(
            @RequestParam(required = true, defaultValue = "") @ApiParam(required = true, defaultValue = "", value = "角色ID") String roleId) {
        try {
            return roleService.delRole(roleId);
        } catch (Exception e) {
            logger.error("删除异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "删除异常！请联系管理员");
        }
    }

}
