package com.golaxy.machine.util.pagehelper;

import com.github.pagehelper.PageInfo;
import com.golaxy.machine.util.UtilsApi;

import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[对于页面取值封装类]
 * @date 2021/7/22 上午10:13
 */
public class PageUtil {
    /**
     * 将分页信息封装到统一的接口
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
