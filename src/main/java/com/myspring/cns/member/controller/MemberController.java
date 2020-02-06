package com.myspring.cns.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.myspring.cns.HomeController;
import com.myspring.cns.member.dao.MemberDAO;
import com.myspring.cns.member.service.MemberService;
import com.myspring.cns.member.vo.MemberVO;
import com.myspring.cns.member.vo.RestReturnMemberVO;
import com.myspring.cns.member.vo.RestReturnTokenVO;
import com.myspring.cns.member.vo.TokenVO;
import com.myspring.cns.member.vo.TokenVO2;

@RestController
//@RequestMapping(value="/rest/*")
@RequestMapping
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberVO membervo;
	@Autowired
	TokenVO tokenVO;
	@Autowired
	RestReturnMemberVO rrmvo;
	@Autowired
	RestReturnTokenVO rrtvo;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public RestReturnMemberVO signup(
			@RequestBody TokenVO2 tokenvo2,
//			@RequestBody TokenVO tokenvo2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		membervo.setUsername(tokenvo2.getUsername());
		membervo.setPassword(tokenvo2.getPassword());

		logger.info("username , password : "+tokenvo2.getUsername()+", "+tokenvo2.getPassword());

		MemberVO memberVO = memberService.addMember(membervo);
		memberVO.setPassword(null);

		rrmvo.setCode(200);
		rrmvo.setMessage("Success");
		rrmvo.setData(memberVO);
		return rrmvo;
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public RestReturnMemberVO selectUserInfo(
//			@RequestBody TokenVO2 tokenvo2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info(" user - get. selectUserInfo 메서도 호출 됨 ");
		String id = request.getParameter("id");
//		Long long_id = Long.parseUnsignedLong(id);
		
		MemberVO memVO = memberService.getUserInfoById(id);
		
		
		rrmvo.setData(memVO);
		rrmvo.setCode(200);
		rrmvo.setMessage("Success");
		return rrmvo;

	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public RestReturnTokenVO checkUserInfo(
			@RequestBody TokenVO2 tokenvo2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
//			System.out.println("/auth 요청이 컨으로 들어옴");
			logger.info("/auth 요청이 컨으로 들어옴");
			// 여기까지는 들어온다. 이걸 db에 넣고 빼고 하면 된다.
			membervo.setUsername(tokenvo2.getUsername());
			membervo.setPassword(tokenvo2.getPassword());
			System.out.println(tokenvo2.getUsername());
			logger.info(tokenvo2.getUsername());
//			String token = tokenvo2.getUsername()+randomSM();
			
			tokenVO = memberService.authorizeMember(membervo);
		
			logger.info( tokenVO.toString());
			logger.info( membervo.toString());

			rrtvo.setData(tokenVO);
			rrtvo.setCode(200);
			rrtvo.setMessage("Success");

			return rrtvo;
	}
	
	
	
}
 