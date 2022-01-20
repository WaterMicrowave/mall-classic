package com.hlkj.mallclassic.common;

import org.springframework.data.domain.Page;

import java.util.List;

public class Paging<T> {
    private int currPage;
    private int pageSize;
    private long totalCount;
    private int totalPages;
    private List<T> items;

    public Paging(Page<T> page){
        this.initPageParameters(page);
        this.items = page.getContent();
    }

    public Paging() {
    }

    void initPageParameters(Page<T> page){
        this.currPage = page.getNumber();
        this.pageSize = page.getSize();
        this.totalCount = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    @Override
    public String toString() {
        return "Paging{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", items=" + items +
                '}';
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}