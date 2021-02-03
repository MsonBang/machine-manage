package com.golaxy.machine.miaoshao.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 上午10:46
 */
@ApiModel(description = "登录参数VO")
@Data
public class LoginVO {

    @ApiModelProperty(name = "username", value = "用户登录名", dataType = "string")
    private String username;

    @ApiModelProperty(name = "password", value = "用户密码", dataType = "string")
    private String password;

}
