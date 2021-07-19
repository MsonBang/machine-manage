package com.golaxy.machine.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO[服务器账户信息表]
 * @date 2021/7/19 上午11:08
 */
@ApiModel(description = "服务器账户信息实体类")
@Data
public class ServerAccountInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "serverid", value = "关联服务器ID", dataType = "string")
    private String serverid;

    @ApiModelProperty(name = "account", value = "账户名称", dataType = "string")
    private String account;

    @ApiModelProperty(name = "password", value = "账户名称", dataType = "string")
    private String password;

    @ApiModelProperty(name = "isadmin", value = "是否管理员[0否1是]", dataType = "string")
    private String isadmin;

    @ApiModelProperty(name = "createtime", value = "创建时间", dataType = "string")
    private Date createtime;

    @ApiModelProperty(name = "createuser", value = "创建人", dataType = "string")
    private String createuser;

    @ApiModelProperty(name = "updatetime", value = "更新时间", dataType = "string")
    private Date updatetime;

    @ApiModelProperty(name = "updateuser", value = "更新人", dataType = "string")
    private String updateuser;

    @ApiModelProperty(name = "delflag", value = "删除标志[0否1是]", dataType = "string")
    private int delflag;

}
