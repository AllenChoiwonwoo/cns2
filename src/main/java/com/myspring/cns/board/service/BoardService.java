package com.myspring.cns.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cns.board.dao.BoardDAO;
import com.myspring.cns.board.vo.BoardVO;
import com.myspring.cns.member.dao.MemberDAO;
import com.myspring.cns.member.service.MemberService;
import com.myspring.cns.member.vo.MemberVO;
import com.myspring.cns.member.vo.RestReturnMemberVO;
import com.myspring.cns.member.vo.TokenVO;

@Service("BoardService")
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private TokenVO tokenVO;
	@Autowired
	private BoardVO boardVO;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private RestReturnMemberVO rrmvo;
	
	
	
	
	public BoardVO addNewPost(String accessToken, BoardVO bvo) {
		// TODO Auto-generated method stub
		TokenVO tokenvo = memberDAO.selectUserIdByToken(accessToken);
		logger.info("!!!!  "+tokenvo.toString());
		bvo.setUserId(tokenvo.getUserId());
		logger.info("bvo = "+bvo.toString());
		int boardId = boardDAO.insertPost(bvo);
		logger.info("생성된 포스트 id 값 = "+boardId);
		boardVO = boardDAO.selectOnePostById(boardId);
		logger.info("boardVO = "+boardVO.toString());
//		rrmvo.setCode(200);
//		rrmvo.setMessage("Success");
//		rrmvo.setData(boardVO);
		return boardVO;
	}


	public List<BoardVO> getAllPost() {
		// TODO Auto-generated method stub
		List<BoardVO> resultList = boardDAO.selectAllPost();
		resultList = setMemberVOtoUserInBoardVOList(resultList);
		return resultList;
	}


	public List<BoardVO> getMyAllPost(String accessToken) {
		// TODO Auto-generated method stub
		tokenVO = memberDAO.selectUserIdByToken(accessToken);
		logger.info(tokenVO.toString());
		List<BoardVO> myPostList = boardDAO.getMyAllPost(tokenVO);
		myPostList = setMemberVOtoUserInBoardVOList(myPostList);
		return myPostList;
	}
	
	private List<BoardVO> setMemberVOtoUserInBoardVOList(List<BoardVO> listBVO){
		int i=0;
		for (BoardVO bvo : listBVO) {
//			int gotUserId = bvo.getUserId();
			memberVO = memberDAO.getUserInfoById(bvo.getUserId()+"");
			logger.info(memberVO.toString());
			memberVO.setPassword(null);
			listBVO.get(i).setUser(memberVO);
			i++;
		}
		return listBVO;
	}
	private BoardVO setMemberVOtoUserInBoardVO(BoardVO bvo){
			memberVO = memberDAO.getUserInfoById(bvo.getUserId()+"");
			logger.info(memberVO.toString());
			memberVO.setPassword(null);
			bvo.setUser(memberVO);
		return bvo;
	}

	public BoardVO getOnePostById(int id) {
		// TODO Auto-generated method stub
		boardVO = boardDAO.selectOnePostById(id);
		boardVO = setMemberVOtoUserInBoardVO(boardVO);
		logger.info(".getOnePostById = "+boardVO);
		return boardVO;
	}


	public int deleteOnePostById(int id) {
		// TODO Auto-generated method stub
		int result = boardDAO.deleteOnePostById(id);
		return result;
	}


	public int editOnePostById(BoardVO boardVO2) {
		// TODO Auto-generated method stub
		return boardDAO.updateOnePostById(boardVO2);
	}


	
	
	
	

}
