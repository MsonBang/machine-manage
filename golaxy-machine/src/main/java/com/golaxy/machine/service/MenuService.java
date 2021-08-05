package com.golaxy.machine.service;

import com.golaxy.machine.common.entity.MenuInfoChild;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[菜单管理接口类]
 * @date 2021/8/4 上午10:47
 */
@Service
public interface MenuService {

    /**
     * @Description: 查询菜单列表接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午10:57
     **/
    JsonResult<PageResult> queryList(Map<String, Object> map);

    /**
     * @Description: 新增菜单接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:06
     **/
    JsonResult<Integer> addMenu(Map<String, Object> map);

    /**
     * @Description: 修改菜单接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:09
     **/
    JsonResult<Integer> editMenu(Map<String, Object> map);

    /**
     * @Description: 批量删除菜单接口定义
     * @Params: [list]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:13
     **/
    JsonResult<Boolean> batchDelMenu(List<String> list);

    /**
     * @Description: 查询菜单树接口定义
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.MenuInfoChild>>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 上午11:20
     **/
    JsonResult<List<MenuInfoChild>> findMenuList(Map<String, Object> map);
}
