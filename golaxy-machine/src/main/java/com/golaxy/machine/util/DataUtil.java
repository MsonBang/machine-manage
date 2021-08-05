package com.golaxy.machine.util;

import com.golaxy.machine.common.entity.MenuInfoChild;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description: 数据转化结构工具类
* @Params:
* @Return:
* @Author: miaoxuebing
* @Date: 2021/8/5 上午10:00
**/
public class DataUtil {

    /**
    * @Description: 结果根据指定key 转化集合
    * @Params: [list, key]
    * @Return: java.util.List<java.lang.String>
    * @Author: miaoxuebing
    * @Date: 2021/8/5 上午10:01
    **/
    public static List<String> changeObjStringIds(List<Map> list,String key){
        List<String> result = null;
        if(list ==null){
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            if(map.containsKey(key)){
                result.add(map.get(key)==null?"":map.get(key).toString());
            }
        }
        return result;
    }

    /**
    * @Description: 操作菜单数据
    * @Params: [list, ids]
    * @Return: void
    * @Author: miaoxuebing
    * @Date: 2021/3/4 上午10:24
    **/
    public static void dealMenuDataToId(List<MenuInfoChild> list, List<String> ids){
        for (int i = 0; i < list.size(); i++) {
            ids.add(list.get(i).getId());
        }
    }
}
