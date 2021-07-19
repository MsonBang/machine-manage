package com.golaxy.machine.service.impl;

import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.common.entity.UserInfo;
import com.golaxy.machine.service.ManagerService;
import com.golaxy.machine.mapper.ManagerMapper;
import com.golaxy.machine.util.JwtUtils;
import com.golaxy.machine.util.RedisUtil;
import com.golaxy.machine.util.UtilsApi;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public JsonResult<Map<String, Object>> login(Map<String,Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
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
        String token = JwtUtils.createToken(UtilsApi.toString(userInfo.getId()), UtilsApi.toString(password));
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
    public JsonResult<Integer> addUser(Map<String, Object> map) {
        String username = (String) map.get("username");
        String nickname = (String) map.get("nickname");
        String password = (String) map.get("password");
        String repassword = (String) map.get("repassword");
        if (UtilsApi.isNull(username)){
            return new JsonResult<>(JsonResult.FAIL, "添加失败，请填写登录账号!");
        }
        if (UtilsApi.isNull(nickname)) {
            return new JsonResult<>(JsonResult.FAIL, "添加失败，请填写姓名");
        }
        if (UtilsApi.isNull(password) || UtilsApi.isNull(repassword)) {
            return new JsonResult<>(JsonResult.FAIL, "添加失败，密码不允许为空");
        }
        if (!password.equals(repassword)) {
            return new JsonResult<>(JsonResult.FAIL, "添加失败，两次密码不一致");
        }

        String userId = UtilsApi.getUUIDStr();
        //首先查询该用户是否存在
        UserInfo userInfo = managerMapper.getUserInfo(username);
        if (userInfo != null) {
            return new JsonResult<>(JsonResult.FAIL, "添加失败，账号已存在");
        }
        //加密盐值
        String salt = UtilsApi.getUUIDStr();
        String passwordN = DigestUtils.md5Hex(DigestUtils.md5Hex(repassword).concat(salt));
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(userId);
        userInfo1.setPassword(passwordN);
        userInfo1.setCreatetime(new Date());
        userInfo1.setCreateuser((String) map.get("createuser"));
        userInfo1.setIsadmin("0");
        //开始插入数据库
        int i = managerMapper.insert(userInfo1);
        if (i <= 0) {
            return new JsonResult<>(JsonResult.FAIL, "添加失败");
        }

        return new JsonResult<>(JsonResult.SUCCESS, "添加成功");
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
