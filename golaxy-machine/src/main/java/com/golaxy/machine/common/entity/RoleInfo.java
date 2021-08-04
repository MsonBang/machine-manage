package com.golaxy.machine.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO[角色实体类]
 * @date 2021/3/1 下午3:44
 */
@ApiModel(description = "角色实体类")
@Data
public class RoleInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "rolename", value = "角色名称", dataType = "string")
    private String rolename;

    @ApiModelProperty(name = "rolecode", value = "角色编码", dataType = "string")
    private String rolecode;

    @ApiModelProperty(name = "rolestatus", value = "角色状态是否启动0否1是", dataType = "int")
    private String rolestatus;

    @ApiModelProperty(name = "roleorder", value = "角色排序", dataType = "int")
    private int roleorder;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createtime", value = "创建时间", dataType = "date")
    private Date createtime;

    @ApiModelProperty(name = "createuser", value = "创建人", dataType = "string")
    private String createuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "updatetime", value = "更新时间", dataType = "date")
    private Date updatetime;

    @ApiModelProperty(name = "updateuser", value = "更新人", dataType = "string")
    private String updateuser;

    @ApiModelProperty(name = "delflag", value = "删除标志0否1是", dataType = "int")
    private String delflag;

    @ApiModelProperty(name = "remark", value = "备注", dataType = "string")
    private String remark;

}
