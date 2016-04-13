package com.zhaidou.jobCenter.utils;

import java.util.List;

/**
 * 分页类。
 * 
 * @author kaili
 */
public class Pagination<T> {
    
    /** 页大小 */
    private int pageSize = 10;
    
    /** 当前页数 */
    private int curPageNum;
    
    /** 总页数 */
    private int totalPageNum;
    
    /** 总记录数 */
    private int totalRowNum;

    /** 集合 */
    private List<T> rows;

    /** 起始行索引号 */
    private int startRowNum;
    
    /** 结束行索引号 */
    private int endRowNum;
    
    
    public Pagination() {
    	
    }
    
    public Pagination(int curPageNum, int totalRowNum) {
        this(curPageNum, 10, totalRowNum);
    }
    
    public Pagination(int curPageNum, int pageSize, int totalRowNum) {
        this.pageSize = (pageSize > 0) ? pageSize : 1;
        this.totalRowNum = (totalRowNum > 0)? totalRowNum : 0;
        this.curPageNum = (curPageNum > 0) ? curPageNum : 1;
        this.totalPageNum = (this.totalRowNum%pageSize == 0) ? this.totalRowNum/pageSize  : this.totalRowNum/pageSize + 1;
        this.startRowNum = (this.curPageNum - 1)* pageSize;
        this.endRowNum = startRowNum + this.pageSize -1;
    }
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPageNum() {
        return curPageNum;
    }

    public void setCurPageNum(int curPageNum) {
        this.curPageNum = curPageNum;
    }

    public int getTotalPageNum() {
    	if(pageSize>0&&totalRowNum>0){
    		totalPageNum=(int)((totalRowNum-1)/pageSize)+1;
    	}
    	else{
    		totalPageNum=0;
    	}
    	
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getTotalRowNum() {
        return totalRowNum;
    }

    public void setTotalRowNum(int totalRowNum) {
        this.totalRowNum = totalRowNum;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }
}
