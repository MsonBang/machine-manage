package com.golaxy.machine.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: miaoxuebing
 * @Description: TODO[关系实体类]
 * @Date: 2021/2/5-9:22
 */
@ApiModel(description = "关系实体")
@Data
public class RelationInfo {

    @ApiModelProperty(name = "id", value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(name = "parent_id", value = "上级节点ID", dataType = "string")
    private String parent_id;

    @ApiModelProperty(name = "name", value = "关系名称", dataType = "string")
    private String relationname;

    @ApiModelProperty(name = "orderby", value = "排序序号", dataType = "string")
    private String orderby;

    @ApiModelProperty(name = "create_time", value = "创建时间", dataType = "string")
    private Date create_time;

    @ApiModelProperty(name = "del_flag", value = "删除标志[0否1是]", dataType = "string")
    private String del_flag;

}
