package com.golaxy.machine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golaxy.machine.common.entity.RoleInfo;
import com.golaxy.machine.common.entity.ServerInfo;
import com.golaxy.machine.mapper.RoleMapper;
import com.golaxy.machine.service.RoleService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.UtilsApi;
import com.golaxy.machine.util.pagehelper.PageResult;
import com.golaxy.machine.util.pagehelper.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[角色管理实现类]
 * @date 2021/8/3 下午2:52
 */
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 日志对象
     **/
    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;


    /**
     * @Description: 条件查询角色列表方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<com.golaxy.machine.util.pagehelper.PageResult>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:40
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
        List<RoleInfo> queryList = roleMapper.queryList(map);
        //封装分页数据结果
        PageInfo<RoleInfo> pageInfo = new PageInfo<>(queryList);
        //封装整体JsonResult返回结果
        return new JsonResult<>(JsonResult.SUCCESS, "查询成功", PageUtil.getPageResult(pageInfo));
    }


    /**
     * @Description: 新增角色信息方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午4:51
     **/
    @Override
    public JsonResult<Integer> addRole(Map<String, Object> map) {
        String rolename = (String) map.get("rolename");
        String rolecode = (String) map.get("rolecode");
        //判断参数必填
        if (UtilsApi.isNull(rolename)) {
            logger.info("参数为空，rolename角色名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        if (UtilsApi.isNull(rolecode)) {
            logger.info("参数为空，rolecode角色编码必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        //判断是否已经存在
        RoleInfo roleInfo = roleMapper.findByName(rolename);
        if (roleInfo != null) {
            logger.info("角色名称已经存在，请重新填写！");
            return new JsonResult<>(JsonResult.FAIL, "角色名称已经存在，请重新填写！");
        }
        RoleInfo roleInfo1 = roleMapper.findByCode(rolecode);
        if (roleInfo1 != null) {
            logger.info("角色编码已经存在，请重新填写！");
            return new JsonResult<>(JsonResult.FAIL, "角色编码已经存在，请重新填写！");
        }
        //添加主键
        map.put("id", UtilsApi.getUUIDStr());
        //添加创建时间
        map.put("createtime", new Date());
        //执行入库
        int i = roleMapper.insertRole(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "新增失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "新增成功", i);
    }


    /**
     * @Description: 修改角色信息方法实现
     * @Params: [map]
     * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
     * @Author: miaoxuebing
     * @Date: 2021/8/3 下午5:07
     **/
    @Override
    public JsonResult<Integer> editRole(Map<String, Object> map) {
        String id = (String) map.get("id");
        String rolename = (String) map.get("rolename");
        String rolecode = (String) map.get("rolecode");
        //判断参数必填
        if (UtilsApi.isNull(id)) {
            logger.info("参数为空，角色主键id必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        if (UtilsApi.isNull(rolename)) {
            logger.info("参数为空，rolename角色名称必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        if (UtilsApi.isNull(rolecode)) {
            logger.info("参数为空，rolecode角色编码必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        //查询是否存在角色
        RoleInfo roleInfo = roleMapper.findById(id);
        if (roleInfo == null) {
            logger.info("角色不存在！");
            return new JsonResult<>(JsonResult.FAIL, "角色不存在！");
        }
        //开始添加修改时间
        map.put("updatetime", new Date());
        //执行入库
        int i = roleMapper.updatetRole(map);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "修改失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "修改成功", i);
    }


    /**
    * @Description: 删除角色信息方法实现
    * @Params: [roleId]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午5:11
    **/
    @Override
    public JsonResult<Boolean> delRole(String roleId) {
        if (UtilsApi.isNull(roleId)) {
            logger.info("参数为空，角色主键roleId必填！");
            return new JsonResult<>(JsonResult.FAIL, "参数缺失！请联系管理员");
        }
        //执行入库
        boolean flag = roleMapper.delRole(roleId);
        if (!flag) {
            return new JsonResult<>(JsonResult.FAIL, "删除失败");
        }
        return new JsonResult<>(JsonResult.SUCCESS, "删除成功成功", flag);
    }

    
    /**
    * @Description: 
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午5:15
    **/
    @Override
    public JsonResult<Integer> grantRole(Map<String, Object> map) {
        return null;
    }

    
    /**
    * @Description: 
    * @Params: [map]
    * @Return: com.golaxy.machine.util.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午5:15
    **/
    @Override
    public JsonResult<Integer> grantMenu(Map<String, Object> map) {
        return null;
    }

    
    /**
    * @Description: 
    * @Params: [userId]
    * @Return: com.golaxy.machine.util.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: miaoxuebing
    * @Date: 2021/8/3 下午5:15
    **/
    @Override
    public JsonResult<Map<String, Object>> getUserMenuList(String userId) {
        return null;
    }
}
