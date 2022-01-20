package com.hlkj.mallclassic.vo;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: PageVO
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 16:14
 * @Version: 1.0
 */
public class PageVO<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPages;
    private T simpleT=null ;
    private List<T> tList=null ;
    private Boolean hasMore=false;

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", simpleT=" + simpleT +
                ", tList=" + tList +
                ", hasMore=" + hasMore +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public T getSimpleT() {
        return simpleT;
    }

    public void setSimpleT(T simpleT) {
        this.simpleT = simpleT;
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
