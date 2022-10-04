package com.kh.junespring.board.service;

import java.util.List;

import com.kh.junespring.board.domain.Board;
import com.kh.junespring.board.domain.Reply;

public interface BoardService {
	
	//registerBoard
	public int registerBoard(Board board);
	
	//removeOneByNo
	public int removeOneByNo(int boardNo);

	//printAllBoard
	public List<Board> printAllBoard(int currentPage, int limit);
	
	//getTotalCount
	public int getTotalCount(String searchCondition, String searchValue);
	
	//printOneByNo
	public Board printOneByNo(Integer boardNo);
	
	//modifyBoard
	public int modifyBoard(Board board);
	
	public List<Board> printAllByValue(String searchCondition, String searchValue, int currentPage, int boardLimit);
	
	// 댓글관리
	public int registerReply(Reply reply);
	
	// 댓글목록조회
	public List<Reply> printAllReply(int refBoardNo);

	public int modifyReply(Reply reply);
	
	public int removeReply(Integer replyNo);
}
