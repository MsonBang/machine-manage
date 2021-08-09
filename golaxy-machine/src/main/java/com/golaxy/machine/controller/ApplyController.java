package com.golaxy.machine.controller;

import com.golaxy.machine.common.entity.ServerApplyInfo;
import com.golaxy.machine.service.ApplyService;
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
 * @author miaoxuebing[服务器申请操作控制器]
 * @Description: TODO[申请服务器使用类，主要对于申请人记录操作做处理]
 * @date 2021/1/31 上午9:22
 */
@RestController
@RequestMapping("/apply")
@Api(value = "[申请记录管理模块接口]", tags = "[申请记录管理模块接口]", description = "[申请记录管理]")
public class ApplyController {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(ApplyController.class);
    @Autowired
    private ApplyService applyService;


    /**
    * @Description: 申请服务器记录列表条件查询
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.util.List<com.golaxy.machine.common.entity.ServerInfo>>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:21
    **/
    @PostMapping("/list")
    @ApiOperation(value = "查询服务器列表信息接口", notes = "查询服务器列表信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuser", value = "申请人姓名", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyorg", value = "申请人所在部门", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "uptime", value = "提交时间", dataType = "date"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int")
    })
    public JsonResult<PageResult> queryList(@RequestBody Map<String, Object> map) {
        try {
            return applyService.queryList(map);
        } catch (Exception e) {
            logger.error("查询异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "查询异常！请联系管理员");
        }
    }


    /**
    * @Description: 新增申请服务器记录
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:30
    **/
    @PostMapping("/add")
    @ApiOperation(value = "新增申请服务器记录接口", notes = "新增申请服务器记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuserid", value = "申请人ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuser", value = "申请人姓名", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyorg", value = "申请人所在部门", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "applytype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "applyremark", value = "申请用途描述[主要描述服务于那个项目、大概描述]", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "howlong", value = "服务器资源使用周期时间预估[3-months]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "applyvolume", value = "预估占用容量大小", dataType = "String"),
    })
    public JsonResult<Integer> addApply(@RequestBody Map<String, Object> map) {
        try {
            return applyService.addApply(map);
        } catch (Exception e) {
            logger.error("添加异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "添加异常！请联系管理员");
        }
    }


    /**
    * @Description: 更新申请记录
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:45
    **/
    @PostMapping("/edit")
    @ApiOperation(value = "更新申请服务器记录接口", notes = "更新申请服务器记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "申请主键id", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuserid", value = "申请人ID", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuser", value = "申请人姓名", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyorg", value = "申请人所在部门", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "applytype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "applyremark", value = "申请用途描述[主要描述服务于那个项目、大概描述]", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "howlong", value = "服务器资源使用周期时间预估[3-months]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "applyvolume", value = "预估占用容量大小", dataType = "String")
    })
    public JsonResult<Integer> editApply(@RequestBody Map<String, Object> map) {
        try {
            return applyService.editApply(map);
        } catch (Exception e) {
            logger.error("修改异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }


    /**
    * @Description: 审核申请记录
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:45
    **/
    @PostMapping("/check")
    @ApiOperation(value = "审核申请服务器记录接口", notes = "审核申请服务器记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "申请主键id", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuser", value = "申请人姓名", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "checkuser", value = "审核人", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "checkstatus", value = "审核状态[0通过未通过]", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "checkremark", value = "审核意见备注", dataType = "String"),
    })
    public JsonResult<Integer> checkApply(@RequestBody Map<String, Object> map) {
        try {
            return applyService.checkApply(map);
        } catch (Exception e) {
            logger.error("审核异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "审核异常！请联系管理员");
        }
    }


    /**
    * @Description: 撤销申请操作
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:48
    **/
    @PostMapping("/reback")
    @ApiOperation(value = "撤销申请服务器记录接口", notes = "撤销申请服务器记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "申请主键id", dataType = "string")
    })
    public JsonResult<Integer> rebackApply(@RequestBody Map<String, Object> map) {
        try {
            return applyService.rebackApply(map);
        } catch (Exception e) {
            logger.error("撤销异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "撤销异常！请联系管理员");
        }
    }


    /**
    * @Description: 提交审核操作
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午2:25
    **/
    @PostMapping("/submit")
    @ApiOperation(value = "提交申请服务器记录接口", notes = "提交申请服务器记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "申请主键id", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "servername", value = "服务器名称", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "serverip", value = "服务器ip", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyuser", value = "申请人姓名", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "applyorg", value = "申请人所在部门", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "applyremark", value = "申请用途描述[主要描述服务于那个项目、大概描述]", dataType = "String")
    })
    public JsonResult<Integer> submitApply(@RequestBody Map<String, Object> map) {
        try {
            return applyService.submitApply(map);
        } catch (Exception e) {
            logger.error("提交异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL, "提交异常！请联系管理员");
        }
    }


    /**
    * @Description: 批量删除申请记录
    * @Params: [ids]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:59
    **/
    @PostMapping("/del")
    @ApiOperation(value = "批量删除申请服务器记录接口", notes = "批量删除申请服务器记录接口")
    public JsonResult<Integer> delApply(@RequestBody List<String> ids){
        try {
            //查询字段转化为map集合
            return applyService.delApply(ids);
        } catch (Exception e) {
            logger.error("删除异常！请联系管理员", e);
            return new JsonResult<>(JsonResult.FAIL,"删除异常！请联系管理员");
        }
    }

    /**
    * @Description: 根据主键查询申请记录详情
    * @Params: [applyid]
    * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.common.entity.ServerApplyInfo>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午2:17
    **/
    @GetMapping("/findOne")
    @ApiOperation(value = "查询申请服务器记录详情信息接口", notes = "查询申请服务器记录详情信息接口")
    public JsonResult<ServerApplyInfo> findOne(
            @RequestParam(defaultValue = "") @ApiParam(required = true, defaultValue = "", value = "申请记录ID") String applyid) {
        try {
            return applyService.findOneById(applyid);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询异常！请联系管理员" + e.getMessage());
            return new JsonResult<>(JsonResult.FAIL, "修改异常！请联系管理员");
        }
    }

}
