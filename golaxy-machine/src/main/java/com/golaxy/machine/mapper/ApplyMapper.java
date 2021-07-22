package com.golaxy.machine.mapper;

import com.golaxy.machine.common.entity.ServerApplyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[申请服务器记录mapper类]
 * @date 2021/7/21 下午4:31
 */
@Mapper
public interface ApplyMapper {

    /**
    * @Description: 条件查询申请服务器记录mapper接口定义
    * @Params: [map]
    * @Return: java.util.List<com.golaxy.machine.common.entity.ServerApplyInfo>
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:35
    **/
    List<ServerApplyInfo> queryList(Map<String, Object> map);

    /**
    * @Description: 新增申请服务器记录mapper接口定义
    * @Params: [map]
    * @Return: int
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:37
    **/
    int insertApply(Map<String, Object> map);

    /**
    * @Description: 修改申请服务器记录mapper接口定义
    * @Params: [map]
    * @Return: int
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:37
    **/
    int updateApply(Map<String, Object> map);

    /**
    * @Description: 审核申请服务记录mapper接口定义
    * @Params: [map]
    * @Return: int
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午5:09
    **/
    int checkApply(Map<String, Object> map);

    /**
    * @Description: 撤销申请服务记录mapper接口定义
    * @Params: [map]
    * @Return: int
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午5:18
    **/
    int rebackApply(Map<String, Object> map);

    /**
    * @Description: 批量删除申请服务器记录mapper接口定义
    * @Params: [ids]
    * @Return: int
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:38
    **/
    int batchDelApply(List<String> ids);

    /**
    * @Description: 根据主键查询申请服务器记录详情mapper接口定义
    * @Params: [id]
    * @Return: com.golaxy.machine.common.entity.ServerApplyInfo
    * @Author: miaoxuebing
    * @Date: 2021/7/21 下午4:39
    **/
    ServerApplyInfo findOneById(@Param("id") String id);
}
