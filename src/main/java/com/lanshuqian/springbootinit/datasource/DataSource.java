package com.lanshuqian.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 数据源接口（新接入的数据源必须实现）
 *  此处就是适配器模式。
 *  我们抽出来一个接口，要求所有接入到系统的 服务 都要实现这个接口
 *
 * @param <T>
 */
public interface DataSource<T> {
    /**
     * 搜索
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText, long pageNum, long pageSize);
}
