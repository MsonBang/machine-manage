package com.golaxy.machine.service;

import com.golaxy.machine.common.entity.ServerApplyInfo;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 上午9:33
 */
@Service
public interface ApplyService {

    /**
    * @Description: 查询申请记录列表接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.util.List<com.golaxy.machine.common.entity.ServerInfo>>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:00
    **/
    JsonResult<PageResult> queryList(Map<String, Object> map);

    /**
    * @Description: 新增申请记录接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:29
    **/
    JsonResult<Integer> addApply(Map<String, Object> map);

    /**
    * @Description: 编辑申请记录接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:52
    **/
    JsonResult<Integer> editApply(Map<String, Object> map);

    /**
    * @Description: 审核申请记录接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:54
    **/
    JsonResult<Integer> checkApply(Map<String, Object> map);

    /**
    * @Description: 撤销申请记录接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:54
    **/
    JsonResult<Integer> rebackApply(Map<String, Object> map);

    /**
    * @Description: 批量删除申请记录接口定义
    * @Params: [ids]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午3:58
    **/
    JsonResult<Integer> delApply(List<String> ids);

    /**
    * @Description: 查询申请服务器记录详情接口定义
    * @Params: [applyid]
    * @Return: com.golaxy.machine.common.entity.ServerApplyInfo
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:42
    **/
    JsonResult<ServerApplyInfo> findOneById(String applyid);





}
