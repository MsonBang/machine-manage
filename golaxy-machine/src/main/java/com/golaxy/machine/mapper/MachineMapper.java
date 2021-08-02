package com.golaxy.machine.mapper;

import com.golaxy.machine.common.entity.ServerInfo;
import com.golaxy.machine.util.JsonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[服务器操作mapper]
 * @date 2021/7/19 下午4:15
 */
@Mapper
public interface MachineMapper {

    /**
     * @Description: 查询服务器列表mapper接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.ServerInfo>>
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:17
     **/
    List<ServerInfo> queryList(Map<String, Object> map);

    /**
     * @Description: 新增服务器信息mapper接口定义
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:27
     **/
    int insertServer(Map<String, Object> map);

    /**
     * @Description: 修改服务器信息mapper接口定义
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:46
     **/
    int updateServer(Map<String, Object> map);

    /**
     * @Description: 根据主键查询服务器详细信息接口定义
     * @Params: [id]
     * @Return: com.golaxy.machine.common.entity.ServerInfo
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午4:54
     **/
    ServerInfo findOneById(@Param("id") String id);

    /**
    * @Description: 根据IP地址查询服务器信息
    * @Params: [ip]
    * @Return: com.golaxy.machine.common.entity.ServerInfo
    * @Author: miaoxuebing
    * @Date: 2021/8/2 下午3:34
    **/
    ServerInfo findOneByIp(@Param("ip") String ip);

    /**
     * @Description: 批量删除服务器信息接口定义
     * @Params: [ids]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/7/19 下午5:00
     **/
    int batchDelServer(List<String> ids);
}
