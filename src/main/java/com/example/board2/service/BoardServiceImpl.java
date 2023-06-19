package com.example.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board2.dao.BoardDao;
import com.example.board2.dto.Board;

@Service  //어노테이션 
public class BoardServiceImpl implements BoardService {
	
	@Autowired	//boardMapper에 있는 sql문을 BoardServiceImpl로 읽어와서 의존성주입하여 객체생성 한것.
	private BoardDao boardMapper; // BoardMapper의 의존성 주입
	
	@Override
	public int maxNum() throws Exception {
		return boardMapper.maxNum();
	}

	@Override
	public void insertData(Board board) throws Exception {
		boardMapper.insertData(board);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<Board> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		return boardMapper.getLists(searchKey, searchValue, start, end);
	}
	
	
	
}
