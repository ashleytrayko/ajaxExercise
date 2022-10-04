package com.kh.junespring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.junespring.board.domain.Board;
import com.kh.junespring.board.domain.Reply;

public interface BoardStore {
	public int insertBoard(SqlSession session, Board board);
	
	public int deleteOneByNo(SqlSession session, int boardNo);

	public int updateBoardCount(SqlSession session, int boardNo);

	public List<Board> selectAllBoard(SqlSession session,int currentPage, int limit);
	
	public int selectTotalCount(SqlSession session,String searchCondition, String searchValue);
	
	public Board selectOneByNo(SqlSession session, Integer boardNo);
	
	public int updateBoard(SqlSession session, Board board);
	
	public List<Board> selectAllByValue(SqlSession session, String searchCondition, String searchValue, int currentPage, int boardLimit);
	
	public int insertReply(SqlSession session, Reply reply);
	
	public List<Reply> selectAllReply(SqlSession session, int refBoardNo);
	
	public int updateReply(SqlSession session, Reply reply);
	
	public int deleteReply(SqlSession session, Integer replyNo);
}
