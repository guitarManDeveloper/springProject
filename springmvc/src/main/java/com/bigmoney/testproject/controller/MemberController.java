package com.bigmoney.testproject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bigmoney.testproject.service.MemberService;

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
	public String login_post(@RequestParam Map<String, Object> map,
		HttpServletRequest req ) throws Exception {

		//사용자 정보조회
		Map<String, Object> memberMap =  memberService.getMember(map);
		HttpSession session =  req.getSession();
		if(memberMap != null) {
			//사용자 정보 세션저장
			session.setAttribute("memberMap", memberMap);
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
		session.invalidate();
		return "success";
	}
	
	
}
