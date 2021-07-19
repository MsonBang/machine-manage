package com.golaxy.machine.service;

import com.golaxy.machine.common.entity.ServerInfo;
import com.golaxy.machine.util.JsonResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[服务器增删改查接口类]
 * @date 2021/1/31 上午9:38
 */
@Service
public interface MachineService {

    /**
    * @Description: 查询服务器列表接口定义
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: miaoxuebing
    * @Date: 2021/7/19 下午3:45
    **/
    JsonResult<List<ServerInfo>> queryList(Map<String, Object> map);

    /**
     * @Description: 新增服务器信息接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午2:53
     **/
    JsonResult<Integer> addServer(Map<String, Object> map);

    /**
     * @Description: 修改服务器信息接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午3:02
     **/
    JsonResult<Integer> editServer(Map<String, Object> map);

    /**
     * @Description: 根据主键查询详情接口定义
     * @Params: [id]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午3:25
     **/
    JsonResult<ServerInfo> findOneById(String id);

    /**
     * @Description: 批量删除服务器信息
     * @Params: [ids]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午3:16
     **/
    JsonResult<Integer> delServer(List<String> ids);
}
