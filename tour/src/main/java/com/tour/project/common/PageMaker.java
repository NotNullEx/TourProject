package com.tour.project.common;

import com.tour.project.common.vo.PageCriteriaVO;

public class PageMaker {

	// Criteria cri.getPage() - 현재 페이지 번호
	// Criteria cri.getPerPageNum() - 한 페이지당 보여줄 게시글의 갯수
	private PageCriteriaVO cri;
	
	// 총 게시글 수
    private int totalCount;
    
    // 화면에 보여질 첫번째 페이지 번호, 시작 페이지 번호
    private int startPage;
    
    // 화면에 보여질 마지막 페이지 번호, 끝 페이지 번호
    private int endPage; // 공식: endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
    
    private boolean prev;
    private boolean next;
    
    // 화면 하단에 보여지는 페이지 버튼의 수
    private int displayPageNum = 4;
    
    
	public PageCriteriaVO getCri() {
		return cri;
	}
	
	public void setCri(PageCriteriaVO cri) {
		this.cri = cri;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		
		// cri.getPage() - 현재 페이지 번호
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        
        // cri.getPerPageNum() - 한 페이지당 보여줄 게시글의 갯수
        // totalCount - 총 게시글 수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
    
    
}
