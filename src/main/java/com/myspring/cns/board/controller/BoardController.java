package com.myspring.cns.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.cns.board.service.BoardService;
import com.myspring.cns.board.vo.BoardVO;
import com.myspring.cns.member.controller.MemberController;
import com.myspring.cns.member.dao.MemberDAO;
import com.myspring.cns.member.vo.MemberVO;
import com.myspring.cns.member.vo.RestReturnMemberVO;
import com.myspring.cns.member.vo.TokenVO;

@RestController
@RequestMapping
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardVO myBoardVO;
	@Autowired
	BoardService boardService;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MemberVO memberVO;
	@Autowired
	TokenVO tokenVO;
	@Autowired
	RestReturnMemberVO rrmvo;
	
	
	@RequestMapping(value = "/post", method=RequestMethod.POST)
		public RestReturnMemberVO writePost(
				@RequestBody BoardVO boardVO,
				HttpServletRequest request, HttpServletResponse response) {
		logger.info("boardVO = "+boardVO);
		//1.토큰을 받아온다.
		String accessToken = request.getHeader("accesstoken");
		logger.info("accessToken = "+accessToken);
//		System.out.println("accessToken = "+accessToken);
		//2. 토큰과 매칭되는 userid를 받아온다.
		
		
		boardVO = boardService.addNewPost(accessToken, boardVO);
		//3. db.post 에 데이터를 넣는다. 그리고 post의 id를 받아온다.
		
		// 4. post. id 를 통해서 해당 low의 데이터를 전부 받아온다.
		
		// 5. api 요구사항에 맞게 객체에 데이터들을 담아 return 한다.
		rrmvo.setCode(200);
		rrmvo.setMessage("Success");
		rrmvo.setData(boardVO);
				
		return rrmvo;
	}
	@RequestMapping(value = "/post", method=RequestMethod.GET)
	public RestReturnMemberVO getAllPost(
//			@RequestBody BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) {
		
		/***
		 * 1. 요청을 받는다.
		 * 2. db 에서 모든 글들을 id의 내림차순으로 가져온다.
		 * 3. 화면에 뿌려준다.
		 */
		List boardvoList = boardService.getAllPost();
		logger.info(boardvoList.toString());
		rrmvo.setData(boardvoList);
		
		return rrmvo;
	}
	
	@RequestMapping(value = "/post/my", method=RequestMethod.GET)
	public RestReturnMemberVO getMyAllPost(
//			@RequestBody BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) {
			
			String accessToken = request.getHeader("accesstoken");
			logger.info("accessToken = "+accessToken);
//			boardVO = boardService.addNewPost(accessToken, boardVO);
		
			List myPostList = boardService.getMyAllPost(accessToken);
			rrmvo.setData(myPostList);
		
		return rrmvo;
	}
//	@RequestMapping(value = "/post/detail/{id}", method=RequestMethod.GET)
//	public RestReturnMemberVO getOnePostById(
//			HttpServletRequest request, HttpServletResponse response) {
//		
//		return rrmvo;
//	}
	@RequestMapping(value = "/post/{id}", method=RequestMethod.GET)
	public RestReturnMemberVO getOnePostById(
			@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
			
		myBoardVO = boardService.getOnePostById(id);
		rrmvo.setData(myBoardVO);
		return rrmvo;
	}
	
	@RequestMapping(value = "/post/{id}", method=RequestMethod.DELETE)
	public RestReturnMemberVO deleteOnePostById(
			@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
			
		int result = boardService.deleteOnePostById(id);
		rrmvo.setData(result);
		return rrmvo;
	}

	@RequestMapping(value = "/post", method=RequestMethod.PUT)
	public RestReturnMemberVO editOnePostById(
			@RequestBody BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("/post, put 호출됨 " );
		int result = boardService.editOnePostById(boardVO);
		rrmvo.setData(result);
		return rrmvo;
	}
	// put , /post/detail/{id} 부터 하면 된다.
			
	

}
