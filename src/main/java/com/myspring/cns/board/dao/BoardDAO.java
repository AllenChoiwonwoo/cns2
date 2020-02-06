package com.myspring.cns.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cns.board.vo.BoardVO;
import com.myspring.cns.member.vo.TokenVO;

@Repository("BoardDAO")
public class BoardDAO {
	
	private  Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	private String mybatisRocation = "mapper.board.";
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private BoardVO boardVO;
	
	public void selectUserIdByToken(String accessToken) {
		// TODO Auto-generated method stub
		
		
	}

	public int insertPost(BoardVO boardVO2) {
		// TODO Auto-generated method stub
		sqlSession.insert(mybatisRocation+"insertOnePost", boardVO2);
		return boardVO2.getId();
	}

	public BoardVO selectOnePostById(int id) {
		// TODO Auto-generated method stub
		logger.info("id = "+id);
		boardVO = sqlSession.selectOne(mybatisRocation+"selectOnePostById", id); // db 의 col 과 데이터 객체의 이름이 다르다.
		logger.info("boardVO = "+boardVO);
		
		return boardVO;
	}
	public List<BoardVO> selectAllPost() {
		List<BoardVO> resultList = sqlSession.selectList(mybatisRocation+"selectAllPost");
		return resultList;
	}


	public List<BoardVO> getMyAllPost(TokenVO tokenVO) {
		List<BoardVO> resultList = sqlSession.selectList(mybatisRocation+ "selectMyAllPost", tokenVO);
		return resultList;
	}

	public int deleteOnePostById(int id) {
		int result = sqlSession.delete(mybatisRocation+"deleteOnePostById", id);
		return result;
	}

	public int updateOnePostById(BoardVO boardVO2) {
		return sqlSession.update(mybatisRocation+"updateOnePostById", boardVO2);
	}
	
	
}
