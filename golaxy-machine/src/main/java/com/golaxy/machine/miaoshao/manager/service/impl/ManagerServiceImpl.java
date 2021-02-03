package com.golaxy.machine.miaoshao.manager.service.impl;

import com.golaxy.machine.miaoshao.common.entity.JsonResult;
import com.golaxy.machine.miaoshao.common.entity.UserInfo;
import com.golaxy.machine.miaoshao.manager.service.ManagerService;
import com.golaxy.machine.miaoshao.manager.vo.LoginVO;
import com.golaxy.machine.miaoshao.mapper.ManagerMapper;
import com.golaxy.machine.miaoshao.util.RedisUtil;
import com.golaxy.machine.miaoshao.util.UtilsApi;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: 用户管理实现类
 * @date 2021/1/31 上午9:40
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @Description: 用户登陆
     * @Params: [loginVO]
     * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<com.golaxy.machine.miaoshao.common.entity.UserInfo>
     * @Author: miaoxuebing
     * @Date: 2021/1/31 下午2:16
     **/
    @Override
    public JsonResult<Map<String, Object>> login(LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        //首先查询该用户是否存在
        UserInfo userInfo = managerMapper.getUserInfo(username);
        if (userInfo == null) {
            return new JsonResult<>(JsonResult.FAIL, "登录失败，用户不存在！");
        }
        //如果存在用户，则判断密码是否一致
        if (!DigestUtils.md5Hex(DigestUtils.md5Hex(password).concat(UtilsApi.toString(userInfo.getSalt()))).
                equals(UtilsApi.toString(password))) {
            return new JsonResult<>(JsonResult.FAIL, "登录失败，密码错误！");
        }
        //如果通过检验，则算是登录成功，生成一个token
        String token = UtilsApi.createToken(UtilsApi.toString(userInfo.getId()), UtilsApi.toString(password));
        //封装返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>() {{
            put("userId", userInfo.getId());
            put("nickname", userInfo.getNickname());
            put("token", token);
            put("isAdmin", userInfo.getIsadmin());
        }};
        //放入Redis
        redisUtil.set(userInfo.getId(), userInfo, 180);

        return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,"登录成功",resultMap);
    }

    /**
    * @Description: 新增用户
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:54
    **/
    @Override
    public JsonResult<Integer> addUser(UserInfo userInfo) {
        return null;
    }

    /**
    * @Description: 编辑用户
    * @Params: [userInfo]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:54
    **/
    @Override
    public JsonResult<Integer> editUser(UserInfo userInfo) {
        return null;
    }

    /**
    * @Description: 删除用户
    * @Params: [id]
    * @Return: com.golaxy.machine.miaoshao.common.entity.JsonResult<java.lang.Integer>
    * @Author: miaoxuebing
    * @Date: 2021/1/31 下午5:54
    **/
    @Override
    public JsonResult<Integer> delUser(String id) {
        return null;
    }
}
