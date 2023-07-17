package com.bigmoney.testproject.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bigmoney.testproject.member.service.MemberService;
import com.bigmoney.testproject.member.vo.MemberVO;


@Controller
public class MemberController {
	@Autowired
	MemberService memberService;

	/**
	 * 사용자 로그인화면
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/member/login");
	}

	/**
	 * 사용자 로그인처리
	 * @param map
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public String login_post(@ModelAttribute("memberVO") MemberVO memberVO,
		HttpServletRequest req ) throws Exception {

		//사용자 정보조회
		MemberVO member =  memberService.getMember(memberVO);
		HttpSession session =  req.getSession();
		if(member != null) {
			//사용자 정보 세션저장
			session.setAttribute("loginVO", member);
			return "success";
		}

		throw new Exception("로그아웃 실패");			
	}

	/**
	 * 사용자 로그아웃
	 * @param req
	 * @return
	 */
	@PostMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest req) {
		HttpSession session =  req.getSession();
		session.setAttribute("member", null);
		return "success";
	}
	
	
}
