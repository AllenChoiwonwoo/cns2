package com.myspring.cns.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cns.member.vo.MemberVO;
import com.myspring.cns.member.vo.TokenVO;

@Repository("MemberDAO")
public class MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private TokenVO myTokenVO;
	public int insertMember(MemberVO membervo) {
		int result = sqlSession.insert("mapper.member.insertMember", membervo);
//		sqlSession.
		logger.info(membervo.getId()+"");
		System.out.println("int result=  "  +result);
		return result;
	}

	public MemberVO getUserInfoById(String id) {
		MemberVO vo = sqlSession.selectOne("mapper.member.getUserInfo", id);
		return vo;
	}

	public MemberVO selectOneUserInfoByUsernamePassword(MemberVO membervo) {
		MemberVO resultVO = sqlSession.selectOne("mapper.member.selectOneUserInfoByUsernamePassword", membervo);
		return resultVO;
	}

	public int insertToken(TokenVO tokenVO) {
		int result = sqlSession.insert("mapper.member.insertToken", tokenVO);
		return result;
	}

	public TokenVO getUserToken(int id) {
		TokenVO tokenVO;
		System.out.println("dao.getusertoken 의 id  =  "+id);
		try {
			tokenVO = sqlSession.selectOne("mapper.member.selectToken", id);
			System.out.println("dat.getusertoken = " +tokenVO.toString());
			tokenVO.setUserId(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			System.out.println("에러가 발생했습니다. !!!!!!!!!");
			logger.info("에러 발생, 에러를 발생시켰습니다");
			logger.info("db.token 테이블에 해당 id의 값이 없습니다.");
			tokenVO=new TokenVO();
			tokenVO.setUserId(id);
			
		}
		return tokenVO;
	}

	public TokenVO selectUserIdByToken(String accessToken) {
		// TODO Auto-generated method stub
		myTokenVO = sqlSession.selectOne("mapper.member.selectUserIdByToken", accessToken);
		return myTokenVO;
	}

	

}
