package com.golaxy.machine.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO[服务器实体类]
 * @date 2021/7/19 上午11:07
 */
@ApiModel(description = "服务器实体类")
@Data
public class ServerInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "servername", value = "服务器名称", dataType = "string")
    private String servername;

    @ApiModelProperty(name = "serverip", value = "服务器ip", dataType = "string")
    private String serverip;

    @ApiModelProperty(name = "memory", value = "服务器内存大小描述", dataType = "string")
    private String memory;

    @ApiModelProperty(name = "gpu", value = "服务器GPU显卡描述", dataType = "string")
    private String gpu;

    @ApiModelProperty(name = "store", value = "服务器存储大小描述", dataType = "string")
    private String store;

    @ApiModelProperty(name = "usetype", value = "服务器用途[0数据库1算法2应用程序3综合混合4其他]", dataType = "int")
    private String usetype;

    @ApiModelProperty(name = "isused", value = "是否允许申请[0否1是]", dataType = "int")
    private String isused;

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




}
