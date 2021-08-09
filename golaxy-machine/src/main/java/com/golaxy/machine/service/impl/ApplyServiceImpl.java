package com.golaxy.machine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golaxy.machine.common.entity.ServerApplyInfo;
import com.golaxy.machine.mapper.ApplyMapper;
import com.golaxy.machine.service.ApplyService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.MailUtil;
import com.golaxy.machine.util.UtilsApi;
import com.golaxy.machine.util.pagehelper.PageResult;
import com.golaxy.machine.util.pagehelper.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[申请服务器记录接口实现类]
 * @date 2021/1/31 上午9:34
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(ApplyServiceImpl.class);
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private MailUtil mailUtil;

    /**
     * @Description: 查询申请服务器记录实现方法
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.ServerApplyInfo>>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午4:33
     **/
    @Override
    public JsonResult<PageResult> queryList(Map<String, Object> map) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        //判断必填参数
        if (UtilsApi.isNull(String.valueOf(pageNum)) || UtilsApi.isNull(String.valueOf(pageSize))) {
            logger.info("查询参数pageNo或pageSize缺失");
            return new JsonResult<>(JsonResult.FAIL, "查询参数缺失！请联系管理员");
        }
        //mybatis分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<ServerApplyInfo> queryList = applyMapper.queryList(map);
        //封装分页数据结果
        PageInfo<ServerApplyInfo> pageInfo = new PageInfo<>(queryList);
        //封装整体JsonResult返回结果
        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", PageUtil.getPageResult(pageInfo));
    }


    /**
     * @Description: 新增申请服务器记录方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午5:01
     **/
    @Override
    public JsonResult<Integer> addApply(Map<String, Object> map) {
        //服务器名称
        String serverName = (String) map.get("servername");
        //服务器IP
        String serverIp = (String) map.get("serverip");
        //服务器用途[0数据库1算法2应用程序3综合混合4其他]
        int applytype = (int) map.get("applytype");
        //申请用途描述[主要描述服务于那个项目、大概描述]
        String applyremark = (String) map.get("applyremark");
        //使用周期，时长
        int howlong = (int) map.get("howlong");

        //判断参数必填
        if (UtilsApi.isNull(serverName)) {
            logger.info("参数为空，servername服务器名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，servername服务器名称必填！");
        }
        if (UtilsApi.isNull(serverIp)) {
            logger.info("参数为空，serverip服务器IP必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，serverip服务器IP必填！");
        }
        if (UtilsApi.isNull(String.valueOf(applytype))) {
            logger.info("参数为空，applytype服务器用途参数必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，applytype服务器用途参数必填！");
        }
        if (UtilsApi.isNull(applyremark)) {
            logger.info("参数为空，applymark服务器用途描述必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，applymark服务器用途描述必填！");
        }
        if (UtilsApi.isNull(String.valueOf(howlong))) {
            logger.info("参数为空，howlong使用周期时长必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，howlong使用周期时长必填！");
        }
        //生成主键ID
        map.put("id", UtilsApi.getUUIDStr());
        //执行入库
        int i = applyMapper.insertApply(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "新增失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "新增成功", i);
    }


    /**
     * @Description: 编辑申请服务器记录方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午4:59
     **/
    @Override
    public JsonResult<Integer> editApply(Map<String, Object> map) {
        //申请记录ID
        String id = (String) map.get("id");
        //服务器名称
        String serverName = (String) map.get("servername");
        //服务器IP
        String serverIp = (String) map.get("serverip");
        //服务器用途[0数据库1算法2应用程序3综合混合4其他]
        int applytype = (int) map.get("applytype");
        //申请用途描述[主要描述服务于那个项目、大概描述]
        String applyremark = (String) map.get("applyremark");
        //使用周期，时长
        int howlong = (int) map.get("howlong");

        //判断参数必填
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，id申请记录主键必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，id申请记录主键必填！");
        }
        if (UtilsApi.isNull(serverName)) {
            logger.info("参数为空，servername服务器名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，servername服务器名称必填！");
        }
        if (UtilsApi.isNull(serverIp)) {
            logger.info("参数为空，serverip服务器IP必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，serverip服务器IP必填！");
        }
        if (UtilsApi.isNull(String.valueOf(applytype))) {
            logger.info("参数为空，applytype服务器用途参数必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，applytype服务器用途参数必填！");
        }
        if (UtilsApi.isNull(applyremark)) {
            logger.info("参数为空，applymark服务器用途描述必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，applymark服务器用途描述必填！");
        }
        if (UtilsApi.isNull(String.valueOf(howlong))) {
            logger.info("参数为空，howlong使用周期时长必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，howlong使用周期时长必填！");
        }
        //执行修改
        int i = applyMapper.updateApply(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "修改失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "修改成功", i);
    }


    /**
     * @Description: 审核-申请记录方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午5:09
     **/
    @Override
    public JsonResult<Integer> checkApply(Map<String, Object> map) {
        //申请记录ID
        String id = (String) map.get("id");
        //审核人
        String checkuser = (String) map.get("checkuser");
        //审核状态[0未通过1通过]
        int checkstatus = (int) map.get("checkstatus");
        //审核意见备注
        String checkremark = (String) map.get("checkremark");

        //判断参数必填
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，id申请记录主键必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，id申请记录主键必填！");
        }
        if (UtilsApi.isNull(checkuser)) {
            logger.info("参数为空，checkuser审核人必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，checkuser审核人必填！");
        }
        if (UtilsApi.isNull(String.valueOf(checkstatus))) {
            logger.info("参数为空，checkstatus审核状态必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，checkstatus审核状态必填！");
        }
        if (UtilsApi.isNull(checkremark)) {
            logger.info("参数为空，checkremark审核备注信息必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，checkremark审核备注信息必填！");
        }
        //添加审核时间
        map.put("checktime", new Date());
        //执行修改
        int i = applyMapper.checkApply(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "审核失败");
        }
        //给申请人发送结果邮件
        mailUtil.makeSendUserParam(map);
        return new JsonResult<>(JsonResult.SUCCESS, "审核成功");
    }


    /**
     * @Description: 撤销-申请记录方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午5:19
     **/
    @Override
    public JsonResult<Integer> rebackApply(Map<String, Object> map) {
        //审核记录主键
        String id = (String) map.get("id");
        //判断必填参数
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，id申请记录主键必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，id申请记录主键必填！");
        }
        //执行修改
        int i = applyMapper.rebackApply(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "撤销失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "撤销成功", i);
    }


    /**
     * @Description: 提交审核操作接口方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午2:28
     **/
    @Override
    public JsonResult<Integer> submitApply(Map<String, Object> map) {
        //审核记录主键
        String id = (String) map.get("id");
        //判断必填参数
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，id申请记录主键必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，id申请记录主键必填！");
        }
        //插入提交时间
        map.put("uptime", new Date());
        map.put("upstatus", 1);
        //执行修改
        int i = applyMapper.updateApply(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "提交审核失败");
        }
        //提交审核之后发送邮件给管理员
        //mailUtil.makeSendAdminParam(map);
        return new JsonResult<>(JsonResult.SUCCESS, "提交审核成功", i);
    }


    /**
     * @Description: 批量删除申请服务器记录方法实现
     * @Params: [ids]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午5:21
     **/
    @Override
    public JsonResult<Integer> delApply(List<String> ids) {
        //判断参数为空
        if (ids.size() == 0 || ids == null) {
            logger.info("参数为空，服务器ID必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，服务器ID必填！");
        }

        int i = applyMapper.batchDelApply(ids);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "删除失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "删除成功", i);
    }


    /**
     * @Description: 查询申请服务器记录详情方法实现
     * @Params: [applyid]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.common.entity.ServerApplyInfo>
     * @Author: miaoxuebing
     * @Date: 2021/7/21 下午5:23
     **/
    @Override
    public JsonResult<ServerApplyInfo> findOneById(String applyid) {
        //判断参数必填
        if (UtilsApi.isNull(applyid)) {
            logger.info("参数为空，服务器主键ID必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，服务器主键ID必填！");
        }
        ServerApplyInfo serverApplyInfo = applyMapper.findOneById(applyid);

        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", serverApplyInfo);
    }
}
