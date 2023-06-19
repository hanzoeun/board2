package com.example.board2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;

@Controller // 어노 테이션
public class BoardController {

	@Autowired // 의존성 주입
	private BoardService boardService;

	@Autowired
	MyUtil myUtil;

	@RequestMapping(value = "/") // localhost로 접속
	public String index() {
		return "/index";
	}

	// 페이지를 보여줌
	// get방식으로 접속을 했을때 뒤에있는 메소드를 실행을 한다. => RequestMethod.GET 실행이된다
	@RequestMapping(value = "/created", method = RequestMethod.GET)
	public String created() {
		return "bbs/created"; // forward로 이동 (객체를가져감)
	}

	// 게시글을 등록하는방법
	@RequestMapping(value = "/created", method = RequestMethod.POST)
	public String createdOK(Board board, HttpServletRequest request, Model model) {

		try {
			int maxNum = boardService.maxNum();

			board.setNum(maxNum + 1); // num컬럼에 돌아갈 값을 1증가 시켜준다.
			board.setIpAddr(request.getRemoteAddr()); // 클라이언트의 ip주소를 구해준다.

			boardService.insertData(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/list"; // 페이지이동을 시켜주는것 (객체를 가져가지못함)
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Board board, HttpServletRequest request, Model model) {

		try {
			String pageNum = request.getParameter("pageNum");

			int currentPage = 1; // 현재 페이지 번호 ( 디폴트는1)

			if (pageNum != null)
				currentPage = Integer.parseInt(pageNum);

			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");

			if (searchKey == null) {
				searchKey = "subject"; // 검색 키워드의 디폴트는 subject
				searchValue = "";
			} else {
				if (request.getMethod().equalsIgnoreCase("GET")) {
					
					// GET방식으로 request가 왔다면
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
					
				}
			}
			//1.전체 게시물의 갯수를 가져온다.(페이징 처리에 필요)
			int dataCount = boardService.getDataCount(searchKey, searchValue);	
			
			//2. 페이징 처리를 한다.(준비 단계)
			int numPerPage = 5; //페이지당 보여줄 데이터의 갯구
			
			int totalPage = myUtil.getPageCount(numPerPage, dataCount); //페이지의 전체 갯수를 구한다.
			
			if(currentPage > totalPage) currentPage = totalPage; //totalPage버디 크면 안된다.
			int start  = (currentPage -1) * numPerPage + 1; //1~ 6~ 11
			int end = currentPage * numPerPage; // 5~10~15
		    //3.전체 게시물 리스트를 가져온다. 
			List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
			
			//4. 페이징 처리를 한다.
			String param = "";
			
			if(searchValue != null && searchValue.equals("")) {
				//searchValue 검색어가 있다면 
				param = "searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");  //컴퓨터의 언어로 인코딩 해주는 작업 
			}
			
			String listUrl = "/list";
			
			// list?searchKey=name&searchValue=춘식 쿼리스트링을 만드는 과정이다 
			if(param.equals("")) listUrl += "?" + param;
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
 			
		} catch (Exception e) {
			
			e.printStackTrace();
			 
		}

		return "bbs/list";
	}

	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public String article() {
		return "bbs/article";
	}

	// 수정 페이지를 보여줌
	@RequestMapping(value = "/updated", method = RequestMethod.GET)
	public String updated(HttpServletRequest request, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if (searchValue != null) {

		}
		return "bbs/updated";
	}

	// 게시글 수정
	@RequestMapping(value = "/updated_ok", method = RequestMethod.POST)
	public String updatedOK() {
		return "bbs/updated";
	}

}
