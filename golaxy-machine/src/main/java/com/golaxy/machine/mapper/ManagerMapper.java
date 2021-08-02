package com.golaxy.machine.mapper;

import com.golaxy.machine.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 下午2:17
 */
@Mapper
public interface ManagerMapper {

    /**
     * @Description: 根绝用户登录名和用户密码查询当前用户mapper定义
     * @Params: [loginVO]
     * @Return: com.golaxy.machine.miaoshao.common.entity.UserInfo
     * @Author: miaoxuebing
     * @Date: 2021/1/31 下午2:19
     **/
    UserInfo getUserInfo(String username);

    /**
     * @Description: 根据主键查询用户信息
     * @Params: [id]
     * @Return: com.golaxy.machine.common.entity.UserInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/2 下午2:36
     **/
    UserInfo getUserInfoById(@Param("id") String id);

    /**
     * @Description: 查询用户信息列表mapper定义
     * @Params: [map]
     * @Return: java.util.List<com.golaxy.machine.common.entity.UserInfo>
     * @Author: miaoxuebing
     * @Date: 2021/7/22 上午11:09
     **/
    List<UserInfo> queryList(Map<String, Object> map);

    /**
     * @Description: 添加用户mapper定义
     * @Params: [userInfo]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/2/3 下午4:46
     **/
    int insert(UserInfo userInfo);

    /**
     * @Description: 修改用户mapper定义
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/7/22 下午2:04
     **/
    int updateUser(Map<String, Object> map);

    /**
     * @Description: 批量删除用户mapper定义
     * @Params: [ids]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/7/22 下午1:43
     **/
    int batchDelUser(List<String> ids);
}
