package com.kh.junespring.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.junespring.board.domain.Board;
import com.kh.junespring.board.domain.Reply;
import com.kh.junespring.board.service.BoardService;
import com.kh.junespring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession session;
	
	@Autowired
	private BoardStore bStore;
	
	@Override
	public int registerBoard(Board board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	@Override
	public int removeOneByNo(int boardNo) {
		int result = bStore.deleteOneByNo(session, boardNo);
		return result;
	}

	@Override
	public int modifyBoard(Board board) {
		int result = bStore.updateBoard(session, board);
		return result;
	}

	@Override
	public List<Board> printAllBoard(int currentPage, int limit) {
		List<Board> bList = bStore.selectAllBoard(session, currentPage, limit);
		return bList;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = bStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public Board printOneByNo(Integer boardNo) {
		int result = 0;
		Board board = bStore.selectOneByNo(session, boardNo);
		if(board != null) {
			result = bStore.updateBoardCount(session, boardNo);
		}
		return board;
	}

	@Override
	public List<Board> printAllByValue(String searchCondition, String searchValue, int currentPage, int boardLimit) {
		List<Board> bList = bStore.selectAllByValue(session, searchCondition, searchValue, currentPage, boardLimit);
		return bList;
	}
	
	// 댓글관리

	@Override
	public int registerReply(Reply reply) {
		int result = bStore.insertReply(session, reply);
		return result;
	}

	@Override
	public int modifyReply(Reply reply) {
		int result = bStore.updateReply(session, reply);
		return result;
	}

	@Override
	public List<Reply> printAllReply(int refBoardNo) {
		List<Reply> rList = bStore.selectAllReply(session, refBoardNo);
		return rList;
	}

	@Override
	public int removeReply(Integer replyNo) {
		int result = bStore.deleteReply(session, replyNo);
		return result;
	}


}
