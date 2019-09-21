package com.example.hy.wanandroid.entity;

import java.util.List;

/**
 * 用户积分列表
 * Created by 陈健宇 at 2019/9/20
 */
public class Coins {


    /**
     * curPage : 1
     * datas : []
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 4
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<Coin> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Coin> getDatas() {
        return datas;
    }

    public void setDatas(List<Coin> datas) {
        this.datas = datas;
    }

}
