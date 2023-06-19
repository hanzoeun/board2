package com.example.board2.util;

import org.springframework.stereotype.Service;

@Service //어노 테이션 
public class MyUtil {
	
	public int getPageCount (int numParPage, int dataCount) {
		int pageCount = 0;
		pageCount = dataCount / numParPage;
		
		if(dataCount % numParPage != 0) {
			pageCount++;
		}
		return pageCount;
	}
		
	public String pageIndexList(int currentPage , int totalPage, String listUrl) {
		//문자열 데이터를 자주 추가하거나  삭제할때는 메모리 낭비를 방지를 위해 StringBuffer를 사용한다
		StringBuffer sb = new StringBuffer(); 
		int numPerBlock = 5; //◀이전 6 7 8 9 10 다음▶ 이전, 다음을 몇개까지 표시할건지에 대한 수 
		
		int currentPageSetup; // ◀이전 버튼에 들어갈 값 
		int page; // 그냥 페이지 숫자를 클릭했을때 들어갈 값 
		
		
		if(currentPage == 0 || totalPage == 0) return ""; //데이터가 없다.
		
		//검색어가 있을경우  : /list?searchKey=name&searchValue=춘식
		if(listUrl.indexOf("?") != -1) {
			//"?"가 들어있다면(쿼리 스트링이 있다면) 
			listUrl += "&";
		} else {
			//쿼리스트링이 없다면
			listUrl += "?";
			
			//검색어가 있을때 : /list?
		}
		
		//1. ◀이전 버튼 만들기
		//currentPage (1~4)  (5~9) (10~14) (15~19) ... 일때 5 10 15 ..
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock ;
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
			//<a href= ""> ◀이전</a> 모양을 만든다 
		}
		//2. 그냥 페이지 이동 버튼 만들기 
		
		page = currentPageSetup + 1; // 1 6 11 16
		
		while(page <= totalPage && page <= currentPageSetup + numPerBlock) {
			if(page == currentPage) {
				//현재 내가 선택한 페이지라면 
				sb.append("<font color=\"red\">" + page + "</font>&nbsp;");
				//<font color = "red">9</font>
			} else {
				//현재 선택한 페이지가 아니면 
				sb.append("<a href=\"" + listUrl + "pageNum" + page + "\">" + page + "</a>&nbsp;");
				//<a href="list?pageNum=7">7</a>&nbsp;
			}
			
			page++;
		}
		//3. ▶다음 버튼 만들기 
		
		//4. 버튼 합쳐서 문자열로 리턴 
		
		return sb.toString();
	}
}
