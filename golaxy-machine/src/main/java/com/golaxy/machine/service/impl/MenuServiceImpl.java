package com.golaxy.machine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golaxy.machine.common.contants.ResultContants;
import com.golaxy.machine.common.entity.MenuInfo;
import com.golaxy.machine.common.entity.MenuInfoChild;
import com.golaxy.machine.mapper.MenuMapper;
import com.golaxy.machine.service.MenuService;
import com.golaxy.machine.util.DataUtil;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.TreeUtils;
import com.golaxy.machine.util.UtilsApi;
import com.golaxy.machine.util.pagehelper.PageResult;
import com.golaxy.machine.util.pagehelper.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[菜单管理实现类]
 * @date 2021/8/4 上午10:48
 */
@Service
public class MenuServiceImpl implements MenuService {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;

    /**
     * @Description: 条件查询菜单信息方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午3:29
     **/
    @Override
    public JsonResult<PageResult> queryList(Map<String, Object> map) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        //判断必填参数
        if (UtilsApi.isNull(String.valueOf(pageNum)) || UtilsApi.isNull(String.valueOf(pageSize))) {
            logger.info("查询参数pageNum或pageSize缺失");
            return new JsonResult<>(JsonResult.FAIL, "查询参数缺失！请联系管理员");
        }
        //mybatis分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<MenuInfo> queryList = menuMapper.queryList(map);
        //封装分页数据结果
        PageInfo<MenuInfo> pageInfo = new PageInfo<>(queryList);
        //封装整体JsonResult返回结果
        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", PageUtil.getPageResult(pageInfo));
    }


    /**
     * @Description: 新增菜单方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:00
     **/
    @Override
    public JsonResult<Integer> addMenu(Map<String, Object> map) {
        //获取参数
        String menuname = (String) map.get("menuname");
        String menucode = (String) map.get("menucode");
        String menutype = (String) map.get("menutype");
        String menuurl = (String) map.get("menuurl");
        //判断参数必填
        if (UtilsApi.isNull(menuname)) {
            logger.info("参数为空，menuname菜单名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menuname菜单名称必填！");
        }
        if (UtilsApi.isNull(menucode)) {
            logger.info("参数为空，menucode菜单编码必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menucode菜单编码必填！");
        }
        if (UtilsApi.isNull(menutype)) {
            logger.info("参数为空，menutype菜单类型必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menutype菜单类型必填！");
        }
        if (ResultContants.MENU_TYPE_CD.equals(menutype)) {
            if (UtilsApi.isNull(menuurl)) {
                logger.info("参数为空，menuurl菜单路径必填！");
                return new JsonResult<>(JsonResult.FAIL, "参数为空，menuurl菜单路径必填！");
            }
        }
        //判断是否已经存在
        MenuInfo menuInfo = menuMapper.findByName(menuname);
        if (menuInfo != null) {
            logger.info("菜单名称已经存在，请重新填写！");
            return new JsonResult<>(JsonResult.FAIL, "菜单名称已经存在，请重新填写！");
        }
        MenuInfo menuInfo1 = menuMapper.findByCode(menucode);
        if (menuInfo1 != null) {
            logger.info("菜单编码已经存在，请重新填写！");
            return new JsonResult<>(JsonResult.FAIL, "菜单编码已经存在，请重新填写！");
        }
        //添加主键
        map.put("id", UtilsApi.getUUIDStr());
        //添加创建时间
        map.put("createtime", new Date());
        //执行入库
        int i = menuMapper.insertMenu(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "新增失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "新增成功", i);
    }


    /**
     * @Description: 更新菜单信息方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:44
     **/
    @Override
    public JsonResult<Integer> editMenu(Map<String, Object> map) {
        //获取参数
        String id = (String) map.get("id");
        String menuname = (String) map.get("menuname");
        String menucode = (String) map.get("menucode");
        String menutype = (String) map.get("menutype");
        String menuurl = (String) map.get("menuurl");
        //判断参数必填
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，id菜单主键必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，id菜单主键必填！");
        }
        if (UtilsApi.isNull(menuname)) {
            logger.info("参数为空，menuname菜单名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menuname菜单名称必填！");
        }
        if (UtilsApi.isNull(menucode)) {
            logger.info("参数为空，menucode菜单编码必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menucode菜单编码必填！");
        }
        if (UtilsApi.isNull(menutype)) {
            logger.info("参数为空，menutype菜单类型必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，menutype菜单类型必填！");
        }
        if (ResultContants.MENU_TYPE_CD.equals(menutype)) {
            if (UtilsApi.isNull(menuurl)) {
                logger.info("参数为空，menuurl菜单路径必填！");
                return new JsonResult<>(JsonResult.FAIL, "参数为空，menuurl菜单路径必填！");
            }
        }
        //添加更新时间时间
        map.put("updatetime", new Date());
        //执行入库
        int i = menuMapper.updateMenu(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "更新失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "更新成功", i);
    }


    /**
     * @Description: 批量删除菜单方法实现
     * @Params: [list]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Boolean>
     * @Author: miaoxuebing
     * @Date: 2021/8/4 下午4:52
     **/
    @Override
    public JsonResult<Boolean> batchDelMenu(List<String> list) {
        if (list == null || list.size() == 0) {
            logger.info("参数为空，待删除菜单集合ids必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数为空，待删除菜单集合ids必填！");
        }
        boolean flag = menuMapper.batchDelMenu(list);
        if (!flag) {
            return new JsonResult<>(JsonResult.FAIL, "删除失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "删除成功", flag);
    }


    /**
     * @Description: 查询菜单树方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.util.List < com.golaxy.machine.common.entity.MenuInfoChild>>
     * @Author: miaoxuebing
     * @Date: 2021/8/5 上午9:35
     **/
    @Override
    public JsonResult<List<MenuInfoChild>> findMenuList(Map<String, Object> map) {
        //查询所有的菜单
        List<MenuInfoChild> list = menuMapper.findList(map);
        //返回结果result集合
        List<MenuInfoChild> result = new ArrayList<>();
        result.addAll(list);
        //遍历上下级关系，组织为树结构
        for (MenuInfoChild menuInfoChild : result) {
            TreeUtils.setMenuChildren(list, menuInfoChild);
        }
        //没有下级的去掉
        List<MenuInfoChild> removeList = new ArrayList<>();
        for (MenuInfoChild menuInfoChild : result) {
            if (menuInfoChild.getChildren().size() == 0) {
                removeList.add(menuInfoChild);
            }
        }
        result.removeAll(removeList);
        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", result);
    }

}
