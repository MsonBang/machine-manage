package com.golaxy.machine.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/31 上午10:41
 */
@ApiModel(description = "用户管理员实体")
@Data
public class UserInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "username", value = "用户登录名", dataType = "string")
    private String username;

    @ApiModelProperty(name = "password", value = "用户密码", dataType = "string")
    private String password;

    @ApiModelProperty(name = "nickname", value = "用户姓名", dataType = "string")
    private String nickname;

    @ApiModelProperty(name = "isadmin", value = "是否管理员[0否1是]", dataType = "int")
    private String isadmin;

    @ApiModelProperty(name = "salt", value = "加密盐值", dataType = "string")
    private String salt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createtime", value = "创建时间", dataType = "date")
    private Date createtime;

    @ApiModelProperty(name = "createuser", value = "创建人", dataType = "string")
    private String createuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "updatetime", value = "修改时间", dataType = "date")
    private Date updatetime;

    @ApiModelProperty(name = "updateuser", value = "修改人", dataType = "string")
    private String updateuser;

    @ApiModelProperty(name = "delflag", value = "删除标志[0否1是]", dataType = "int")
    private String delflag;
}
