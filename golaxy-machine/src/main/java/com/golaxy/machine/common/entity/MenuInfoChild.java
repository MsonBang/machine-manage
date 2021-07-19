package com.golaxy.machine.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/3/3 下午1:54
 */
@Data
public class MenuInfoChild extends MenuInfo {

    private List<MenuInfoChild> children;
}
