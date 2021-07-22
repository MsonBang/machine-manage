package com.golaxy.machine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golaxy.machine.common.entity.ServerInfo;
import com.golaxy.machine.mapper.MachineMapper;
import com.golaxy.machine.service.MachineService;
import com.golaxy.machine.util.JsonResult;
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
 * @Description: TODO[服务器信息操作实现类]
 * @date 2021/1/31 上午9:39
 */
@Service
public class MachineServiceImpl implements MachineService {

    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(MachineServiceImpl.class);
    @Autowired
    private MachineMapper machineMapper;


    /**
     * @Description: 按条件查询服务器列表实现方法
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.ServerInfo>>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:19
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
        List<ServerInfo> queryList = machineMapper.queryList(map);
        //封装分页数据结果
        PageInfo<ServerInfo> pageInfo = new PageInfo<>(queryList);
        //封装整体JsonResult返回结果
        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", PageUtil.getPageResult(pageInfo));
    }


    /**
     * @Description: 新增服务器实现方法
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:26
     **/
    @Override
    public JsonResult<Integer> addServer(Map<String, Object> map) {
        String serverName = (String) map.get("servername");
        String serverIp = (String) map.get("serverip");
        //判断参数必填
        if (UtilsApi.isNull(serverName)) {
            logger.info("参数为空，servername服务器名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        if (UtilsApi.isNull(serverIp)) {
            logger.info("参数为空，serverip服务器IP必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        //插入创建时间
        map.put("createtime", new Date());
        //执行入库
        int i = machineMapper.insertServer(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "新增失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "新增成功");
    }


    /**
     * @Description: 修改服务器信息实现方法
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:45
     **/
    @Override
    public JsonResult<Integer> editServer(Map<String, Object> map) {
        String id = (String) map.get("id");
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，服务器主键ID必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        //添加修改时间
        map.put("updatetime", new Date());
        //执行修改
        int i = machineMapper.updateServer(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "修改失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "修改成功");
    }


    /**
     * @Description: 服务器详情查询实现方法
     * @Params: [id]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:46
     **/
    @Override
    public JsonResult<ServerInfo> findOneById(String id) {
        //判断参数必填
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，服务器主键ID必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        ServerInfo serverInfo = machineMapper.findOneById(id);

        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", serverInfo);
    }


    /**
     * @Description: 批量删除服务器实现方法
     * @Params: [ids]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:47
     **/
    @Override
    public JsonResult<Integer> delServer(List<String> ids) {
        //判断参数为空
        if (ids.size() == 0 || ids == null) {
            logger.info("参数为空，服务器ID必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }

        int i = machineMapper.batchDelServer(ids);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "删除失败失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "删除成功");
    }
}
