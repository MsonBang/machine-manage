package com.golaxy.machine.controller;

import com.golaxy.machine.common.entity.MenuInfoChild;
import com.golaxy.machine.service.MenuService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[系统菜单管理类，针对菜单进行维护操作管理]
 * @date 2021/7/16 下午4:07
 */
@RestController
@RequestMapping("/menu")
@Api(value = "[菜单管理模块接口]", tags = "[菜单管理模块接口]", description = "[菜单管理]")
public class MenuController {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    /**
     * @Description: 条件查询菜单列表信息
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:51
     **/
    @PostMapping("/list")
    @ApiOperation(value = "条件查询菜单列表接口", notes = "条件查询菜单列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "menuname", value = "菜单名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menucode", value = "菜单编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menutype", value = "菜单类型[0菜单1按钮]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", dataType = "int")
    })
    public JsonResult<PageResult> queryMenuList(@RequestBody Map<String, Object> map) {
        try {
            return menuService.queryList(map);
        } catch (Exception e) {
            logger.error("查询异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询异常！请联系管理员");
        }
    }


    /**
     * @Description: 新增菜单
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:02
     **/
    @PostMapping("/add")
    @ApiOperation(value = "新增菜单信息接口", notes = "新增菜单信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "menuname", value = "菜单名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "parentid", value = "父级菜单ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menucode", value = "菜单编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menuurl", value = "路径URL", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menutype ", value = "类型[0菜单1按钮]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "menuorder", value = "排序", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "createuser", value = "创建人", dataType = "string")
    })
    public JsonResult<Integer> addMenu(@RequestBody Map<String, Object> map) {
        try {
            return menuService.addMenu(map);
        } catch (Exception e) {
            logger.error("添加异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "添加异常！请联系管理员");
        }
    }


    /**
     * @Description: 编辑菜单
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:08
     **/
    @PostMapping("/edit")
    @ApiOperation(value = "编辑菜单信息接口", notes = "编辑菜单信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "菜单主键ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menuname", value = "菜单名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "parentid", value = "父级菜单ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menucode", value = "菜单编码", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menuurl", value = "路径URL", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "menutype ", value = "类型[0菜单1按钮]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "menuorder", value = "排序", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "updateuser", value = "创建人", dataType = "string")
    })
    public JsonResult<Integer> editMenu(@RequestBody Map<String, Object> map) {
        try {
            return menuService.editMenu(map);
        } catch (Exception e) {
            logger.error("修改异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }


    /**
     * @Description: 批量删除菜单
     * @Params: [ids]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:14
     **/
    @PostMapping("/del")
    @ApiOperation(value = "批量删除菜单接口", notes = "批量删除菜单接口")
    public JsonResult<Boolean> delUser(@RequestBody List<String> ids) {
        try {
            //查询字段转化为map集合
            return menuService.batchDelMenu(ids);
        } catch (Exception e) {
            logger.error("删除异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "删除异常！请联系管理员");
        }
    }


    /**
     * @Description: 查询菜单树-前端展示
     * @Params: [menuId, menuName]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.MenuInfoChild>>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:21
     **/
    @GetMapping("/treelist")
    @ApiOperation(value = "菜单树查询接口", notes = "菜单树查询接口")
    public JsonResult<List<MenuInfoChild>> getUserList(
            @RequestParam(required = false, defaultValue = "") @ApiParam(defaultValue = "", value = "菜单ID") String menuid,
            @RequestParam(required = false, defaultValue = "") @ApiParam(defaultValue = "", value = "菜单名称") String menuname) {
        try {
            Map<String, Object> paraMap = new HashMap<String, Object>() {{
                put("parentid", menuid);
                put("menuname", menuname);
            }};
            return menuService.findMenuList(paraMap);
        } catch (Exception e) {
            logger.error("查询菜单树异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询菜单树异常！请联系管理员");
        }
    }

}
