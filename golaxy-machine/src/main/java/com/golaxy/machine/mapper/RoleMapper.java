package com.golaxy.machine.mapper;

import com.golaxy.machine.common.entity.MenuInfo;
import com.golaxy.machine.common.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/8/3 下午2:54
 */
@Mapper
public interface RoleMapper {

    /**
     * @Description: 条件查询角色列表mapper
     * @Params: [map]
     * @Return: java.util.List<com.golaxy.machine.common.entity.RoleInfo>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:42
     **/
    List<RoleInfo> queryList(Map<String, Object> map);

    /**
     * @Description: 新增角色信息mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:52
     **/
    int insertRole(Map<String, Object> map);

    /**
     * @Description: 编辑角色信息mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午5:07
     **/
    int updatetRole(Map<String, Object> map);

    /**
     * @Description: 删除角色信息mapper
     * @Params: [roleId]
     * @Return: java.lang.Boolean
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午5:14
     **/
    Boolean delRole(@Param("roleId") String roleId);

    /**
     * @Description: 根据名称查询角色信息mapper
     * @Params: [rolename]
     * @Return: com.golaxy.machine.common.entity.RoleInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:59
     **/
    RoleInfo findByName(String rolename);

    /**
     * @Description: 根据角色编码查询角色信息mapper
     * @Params: [rolecode]
     * @Return: com.golaxy.machine.common.entity.RoleInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:59
     **/
    RoleInfo findByCode(String rolecode);

    /**
     * @Description: 根据主键ID查询角色信息mapper
     * @Params: [id]
     * @Return: com.golaxy.machine.common.entity.RoleInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午5:01
     **/
    RoleInfo findById(String id);

    /**
     * @Description: 删除用户所有绑定角色mapper
     * @Params: [userId]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:04
     **/
    void delUserAllRole(@Param("userId") String userId);

    /**
     * @Description: 插入用户分配角色mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:05
     **/
    int insertUserRole(Map<String, Object> map);

    /**
     * @Description: 删除角色所有绑定菜单mapper
     * @Params: [roleId]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:18
     **/
    void delUserAllMenu(@Param("rolecode") String rolecode);

    /**
     * @Description: 插入角色分配菜单mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:18
     **/
    int insertRoleMenu(Map<String, Object> map);

    /**
     * @Description: 获取用户所有的角色信息
     * @Params: [userId]
     * @Return: java.util.List<java.lang.String>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:34
     **/
    List<String> getUserRole(@Param("userId") String userId);

    /**
     * @Description: 根据角色编码集合获取对应所有菜单项
     * @Params: [list]
     * @Return: java.util.List<com.golaxy.machine.common.entity.MenuInfo>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:37
     **/
    List<MenuInfo> getUserMenu(List<String> list);
}
