package com.myspring.cns.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cns.board.dao.BoardDAO;
import com.myspring.cns.board.vo.BoardVO;
import com.myspring.cns.member.dao.MemberDAO;
import com.myspring.cns.member.vo.MemberVO;
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
	
	
	public BoardVO addNewPost(String accessToken, BoardVO bvo) {
		TokenVO tokenvo = memberDAO.selectUserIdByToken(accessToken);
		/*step3  으로 인한 코드 추가
		 * 내가 글을 쓰면 내 follower들의 feed에 테이가 추가되어야한다.
		 * 1. post 테이블에 테이터를 추가한 후
		 * 2. 방금 추가한 post 의 id 를 받아와서
		 * 3. 토큰을 받아온 내 id로 내 follower들의 id들을 받아온다.
		 * 4. 반복문을 통해 feed 테이블에 {myid, followerid, post_id }를 저장한다.
		 * 여기서 반복문을 통해 모든 followerid[] 의 모든 값은 다 사용해 넣을 수 있게 한다.
		 * 또 나자신은 내가 기본으로 follow 하고 있도록 한다.
		 * 
		 *  */
		logger.info("!!!!  "+tokenvo.toString());
		bvo.setUserId(tokenvo.getUserId());
		logger.info("bvo = "+bvo.toString());
		int boardId = boardDAO.insertPost(bvo);
		logger.info("생성된 포스트 id 값 = "+boardId);
		boardVO = boardDAO.selectOnePostById(boardId);
		logger.info("boardVO = "+boardVO.toString());
		return boardVO;
	}


	public List<BoardVO> getAllPost() {
		List<BoardVO> resultList = boardDAO.selectAllPost();
		resultList = setMemberVOtoUserInBoardVOList(resultList);
		return resultList;
	}


	public List<BoardVO> getMyAllPost(String accessToken) {

		tokenVO = memberDAO.selectUserIdByToken(accessToken);
		logger.info(tokenVO.toString());
		List<BoardVO> myPostList = boardDAO.getMyAllPost(tokenVO);
		myPostList = setMemberVOtoUserInBoardVOList(myPostList);
		return myPostList;
	}
	
	private List<BoardVO> setMemberVOtoUserInBoardVOList(List<BoardVO> listBVO){
		int i=0;
		for (BoardVO bvo : listBVO) {

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
