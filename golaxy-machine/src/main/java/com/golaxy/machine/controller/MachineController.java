package com.golaxy.machine.controller;

import com.golaxy.machine.common.entity.ServerInfo;
import com.golaxy.machine.service.MachineService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[服务器资源管理类，主要有服务器的维护管理操作]
 * @date 2021/1/31 上午9:22
 */
@RestController
@RequestMapping("/machine")
@Api(value = "[服务器管理模块接口]", tags = "[服务器管理模块接口]", description = "[服务器管理]")
public class MachineController {

    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(MachineController.class);

    @Autowired
    private MachineService machineService;


    /**
    * @Description: 服务器管理列表条件查询
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
    * @Author: miaoxuebing
    * @Date: 2021/7/26 下午3:37
    **/
    @PostMapping("/list")
    @ApiOperation(value = "查询服务器列表信息接口", notes = "查询服务器列表信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "usetype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "isused", value = "是否允许申请[0否1是]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int")
    })
    public JsonResult<PageResult> queryList(@RequestBody Map<String, Object> map) {
        try {
            return machineService.queryList(map);
        } catch (Exception e) {
            logger.error("查询异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询异常！请联系管理员");
        }
    }


    /**
    * @Description: 新增服务器信息
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/19 下午2:59
    **/
    @PostMapping("/add")
    @ApiOperation(value = "新增服务器信息接口", notes = "新增服务器信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "memory", value = "服务器内存大小描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "gpu", value = "服务器GPU显卡描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "store", value = "服务器存储大小描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "usetype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "isused", value = "是否允许申请[0否1是]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "createuser", value = "创建人", dataType = "String")
    })
    public JsonResult<Integer> addServer(@RequestBody Map<String, Object> map) {
        try {
            return machineService.addServer(map);
        } catch (Exception e) {
            logger.error("添加异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "添加异常！请联系管理员");
        }
    }


    /**
    * @Description: 修改服务器信息
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/19 下午3:09
    **/
    @PostMapping("/edit")
    @ApiOperation(value = "编辑服务器信息接口", notes = "编辑服务器信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "memory", value = "服务器内存大小描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "gpu", value = "服务器GPU显卡描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "store", value = "服务器存储大小描述", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "usetype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "isused", value = "是否允许申请[0否1是]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "updateuser", value = "更新人", dataType = "String")
    })
    public JsonResult<Integer> editServer(@RequestBody Map<String, Object> map) {
        try {
            return machineService.editServer(map);
        } catch (Exception e) {
            logger.error("修改异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }


    /**
    * @Description: 根据主键查询服务器详情信息
    * @Params: [serverid]
    * @Return: com.golaxy.machine.util.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: miaoxuebing
    * @Date: 2021/7/19 下午3:26
    **/
    @GetMapping("/findOne")
    @ApiOperation(value = "查询服务器详情信息接口", notes = "查询服务器详情信息接口")
    public JsonResult<ServerInfo> findOne(
            @RequestParam(defaultValue = "") @ApiParam(required = true, defaultValue = "", value = "服务器主键ID") String serverid) {
        try {
            return machineService.findOneById(serverid);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询异常！请联系管理员" + e.getMessage());
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }


    /**
    * @Description: 批量删除服务器信息
    * @Params: [ids]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/19 下午3:17
    **/
    @PostMapping("/del")
    @ApiOperation(value = "批量删除服务器信息接口", notes = "批量删除服务器信息接口")
    public JsonResult<Integer> delUser(@RequestBody List<String> ids){
        try {
            //查询字段转化为map集合
            return machineService.delServer(ids);
        } catch (Exception e) {
            logger.error("删除异常！请联系管理员", e.getMessage());
            return new JsonResult<>(JsonResult.FAIL,"删除异常！请联系管理员");
        }
    }




}
