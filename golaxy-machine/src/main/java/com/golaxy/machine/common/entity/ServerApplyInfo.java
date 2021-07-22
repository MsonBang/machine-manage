package com.golaxy.machine.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author miaoxuebing
 * @Description: TODO[服务请申请记录表]
 * @date 2021/7/19 上午11:08
 */
@ApiModel(description = "服务器申请记录实体类")
@Data
public class ServerApplyInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "servername", value = "服务器名称", dataType = "string")
    private String servername;

    @ApiModelProperty(name = "serverip", value = "服务器IP", dataType = "string")
    private String serverip;

    @ApiModelProperty(name = "applyuserid", value = "申请人ID", dataType = "string")
    private String applyuserid;

    @ApiModelProperty(name = "applyuser", value = "声请人姓名", dataType = "string")
    private String applyuser;

    @ApiModelProperty(name = "applyorg", value = "申请人所在部门", dataType = "string")
    private String applyorg;

    @ApiModelProperty(name = "upstatus", value = "提交状态[0未提交1已提交2已撤销]", dataType = "int")
    private int upstatus;

    @ApiModelProperty(name = "uptime", value = "提交时间", dataType = "date")
    private Date uptime;

    @ApiModelProperty(name = "email", value = "邮箱地址", dataType = "string")
    private String email;

    @ApiModelProperty(name = "applytype", value = "申请用途[[0数据库2算法3应用程序4综合混合5其他]]", dataType = "int")
    private int applytype;

    @ApiModelProperty(name = "applyremark", value = "申请用途描述[主要描述服务于那个项目、大概描述]", dataType = "string")
    private String applyremark;

    @ApiModelProperty(name = "howlong", value = "服务器资源使用周期时间预估[3-months]", dataType = "int")
    private int howlong;

    @ApiModelProperty(name = "applyvolume", value = "预估占用容量大小", dataType = "string")
    private String applyvolume;

    @ApiModelProperty(name = "checkuser", value = "审核人", dataType = "string")
    private String checkuser;

    @ApiModelProperty(name = "checktime", value = "审核时间", dataType = "date")
    private Date checktime;

    @ApiModelProperty(name = "checkstatus", value = "审核状态[0未通过1通过]", dataType = "int")
    private int checkstatus;

    @ApiModelProperty(name = "checkremark", value = "审核意见备注", dataType = "string")
    private String checkremark;

    @ApiModelProperty(name = "delflag", value = "删除标志[0否1是]", dataType = "int")
    private int delflag;

}
