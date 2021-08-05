package com.golaxy.machine.util;

import com.golaxy.machine.common.entity.MenuInfoChild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    树工具类
* @Author:         zhangshuailing
* @CreateDate:     2021/1/20 18:56
* @UpdateUser:     zhangshuailing
* @UpdateDate:     2021/1/20 18:56
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class TreeUtils {

    /**
    * @Description: 递归获取菜单树数据
    * @Params: [list, tree]
    * @Return: void
    * @Author: miaoxuebing
    * @Date: 2021/3/3 下午2:07
    **/
    public static void setMenuChildren(List<MenuInfoChild> list, MenuInfoChild tree){
        List<MenuInfoChild> menuInfoChildren = getMenuChilds(list,tree.getId());
        List<MenuInfoChild> children = new ArrayList<>();
        for(MenuInfoChild temp : menuInfoChildren){
            children.add(temp);
        }
        tree.setChildren(children);
        //遍历areaInfo的所有子节点
        for(MenuInfoChild temp1 : menuInfoChildren){
            setMenuChildren(list,temp1);
        }
    }

    /**
    * @Description: 获取菜单子节点
    * @Params: [list, id]
    * @Return: java.util.List<cn.golaxy.jg.entity.orcl.LabelInfoChild>
    * @Author: miaoxuebing
    * @Date: 2021/3/3 下午2:08
    **/
    public static List<MenuInfoChild> getMenuChilds(List<MenuInfoChild> list, String id){
        List<MenuInfoChild> list_children = new ArrayList<>();
        Map<String,String> map_ = new HashMap<>();
        for (MenuInfoChild map:list){
            if(id.equals(map.getParentid()) && !map_.containsKey(map.getId())){
                map_.put(map.getId(),"");
                list_children.add(map);
            }
        }
        return list_children;
    }

}
