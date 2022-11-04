package com.wsw.patrickstar.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页对象
 * @Author: wangsongwen
 * @Date: 2022/7/19 16:26
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 每页数据
     */
    private long pageSize = 10;

    /**
     * 当前页
     */
    private long currentPage = 1;

    /**
     * 分页查询的结果集
     */
    private List<T> result;

    /**
     * 数据总条数
     */
    private Long totalCount = 0L;

    /**
     * 总页数
     */
    private long totalPage = 1;

    public PageInfo() {

    }

    public PageInfo(long currentPage, long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageInfo(long currentPage, long pageSize, Long totalCount, List<T> records) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.result = records;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean haveNextPage() {
        if ((currentPage - 1) * pageSize + result.size() < totalCount) {
            return true;
        } else {
            return false;
        }
    }

    public boolean havePreviousPage() {
        if (currentPage > 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getTotalPage() {
        if (1 == totalPage) {
            setTotalPage();
        }
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalPage() {
        if (pageSize != 0) {
            this.totalPage = (totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize);
        }
    }

    public static <T> PageInfo<T> fromIPage(IPage<T> page) {
        return new PageInfo<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}