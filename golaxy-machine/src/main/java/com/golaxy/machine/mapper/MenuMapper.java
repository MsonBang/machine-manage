package com.golaxy.machine.mapper;

import com.golaxy.machine.common.entity.MenuInfo;
import com.golaxy.machine.common.entity.MenuInfoChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[菜单管理Mapper类]
 * @date 2021/8/4 上午10:48
 */
@Mapper
public interface MenuMapper {

    /**
     * @Description: 查询菜单列表mapper
     * @Params: [map]
     * @Return: java.util.List<com.golaxy.machine.common.entity.MenuInfo>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午3:35
     **/
    List<MenuInfo> queryList(Map<String, Object> map);

    /**
     * @Description: 根据菜单名称查询菜单mapper
     * @Params: [menuname]
     * @Return: com.golaxy.machine.common.entity.MenuInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:01
     **/
    MenuInfo findByName(String menuname);

    /**
     * @Description: 根据菜单编码查询菜单mapper
     * @Params: [menucode]
     * @Return: com.golaxy.machine.common.entity.MenuInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:02
     **/
    MenuInfo findByCode(String menucode);

    /**
     * @Description: 根据主键查询菜单信息mapper
     * @Params: [id]
     * @Return: com.golaxy.machine.common.entity.MenuInfo
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:33
     **/
    MenuInfo findById(String id);

    /**
     * @Description: 插入菜单信息mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:03
     **/
    int insertMenu(Map<String, Object> map);

    /**
     * @Description: 编辑菜单信息mapper
     * @Params: [map]
     * @Return: int
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:49
     **/
    int updateMenu(Map<String, Object> map);

    /**
     * @Description: 批量删除菜单信息mapper
     * @Params: [list]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:49
     **/
    boolean batchDelMenu(List<String> list);

    /**
     * @Description: 获取菜单列表
     * @Params: [map]
     * @Return: java.util.List<com.golaxy.machine.common.entity.MenuInfoChild>
     * @Author: miaoxuebing
     * @Date: 2021/8/5 上午9:46
     **/
    List<MenuInfoChild> findList(Map<String, Object> map);

    /**
     * @Description: 根据菜单ids集合查询菜单
     * @Params: [map]
     * @Return: java.util.List<com.golaxy.machine.common.entity.MenuInfoChild>
     * @Author: miaoxuebing
     * @Date: 2021/8/5 上午11:28
     **/
    List<MenuInfoChild> findListByIds(List<String> list);

}
