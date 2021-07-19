package com.golaxy.machine.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO[菜单资源实体类]
 * @date 2021/3/1 下午3:44
 */
@ApiModel(description = "菜单资源实体类")
@Data
public class MenuInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "parentid", value = "上级菜单ID", dataType = "string")
    private String parentid;

    @ApiModelProperty(name = "menuname", value = "菜单名称", dataType = "string")
    private String menuname;

    @ApiModelProperty(name = "menuurl", value = "菜单路径", dataType = "string")
    private String menuurl;

    @ApiModelProperty(name = "menucode", value = "菜单编码", dataType = "string")
    private String menucode;

    @ApiModelProperty(name = "menutype", value = "菜单类型0菜单1按钮", dataType = "int")
    private String menutype;

    @ApiModelProperty(name = "menuorder", value = "菜单排序", dataType = "int")
    private int menuorder;

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
