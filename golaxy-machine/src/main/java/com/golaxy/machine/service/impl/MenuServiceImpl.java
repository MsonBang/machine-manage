package com.golaxy.machine.service.impl;

import com.golaxy.machine.common.entity.MenuInfoChild;
import com.golaxy.machine.mapper.MenuMapper;
import com.golaxy.machine.service.MenuService;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.pagehelper.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public JsonResult<PageResult> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public JsonResult<Integer> addMenu(Map<String, Object> map) {
        return null;
    }

    @Override
    public JsonResult<Integer> editMenu(Map<String, Object> map) {
        return null;
    }

    @Override
    public JsonResult<Integer> batchDelMenu(List<String> list) {
        return null;
    }

    @Override
    public JsonResult<List<MenuInfoChild>> findMenuList(Map<String, Object> map) {
        return null;
    }
}
