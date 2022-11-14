package com.tour.project.common.vo;

public class PageCriteriaVO {

	// 현재 페이지 번호
	private int page;
	
	// 한 페이지당 보여줄 게시글의 갯수
    private int perPageNum;
    
    // 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
    public int getPageStart() {
        return (this.page-1)*perPageNum;
    }
    
    public PageCriteriaVO() {
        this.page = 1;
        this.perPageNum = 10;
    }
    public int getPage() {
        return page;
    }
    
    // 페이지가 음수값이 되지 않게 설정. 음수가 되면 1페이지를 나타낸다.
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    
    // 페이지당 보여줄 게시글 수가 변하지 않게 설정
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }
}
