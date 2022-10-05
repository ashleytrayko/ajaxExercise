package com.kh.junespring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.kh.junespring.member.domain.Member;
import com.kh.junespring.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
//	private void doGet() {
//		request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response);
//	}
	
	@RequestMapping(value="/member/joinView.kh", method=RequestMethod.GET)
	public String memberJoinView() {
		
		return "member/join";
		// /WEB-INF/views/member/join.jsp -> web.xml에서 앞뒤는 알아서함
	}
	
//	private void doPost() {
//		String memberId = request.getParameter("memberId");
//		int result = new MemberServiceImpl().registerMember(member);
//	}
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public ModelAndView memberJoin(
	@ModelAttribute Member member, //-> 이것을 사용하려면 jsp의 input태그 name값과 VO의 멤버변수명이 같아야 함. 다른게 있다면 Member 객체에서 그 값은 안들어감
//	@RequestParam("memberId") String memberId, 
//	@RequestParam("memberPwd") String memberPwd,
//	@RequestParam("memberName") String memberName,
//	@RequestParam("memberEmail") String memberEmail,
//	@RequestParam("memberPhone") String memberPhone,
	@RequestParam("post") String post,
	@RequestParam("address1") String address1,
	@RequestParam("address2") String address2,
	//Model model,
	ModelAndView mv) {
		try {
			// request.setCharacterEncoding("utf-8");
			//Member member = new Member(memberId, memberPwd, memberName, memberEmail, memberPhone, post + "," + memberAddress);
			member.setMemberAddr(post + "," + address1 + "," + address2);
			int result = mService.registerMember(member);
			if(result > 0) {
				//response.sendRedirect("url");
				//return "redirect:/home.kh";
				mv.setViewName("redirect:/home.kh");
			}else {
				//model.addAttribute("msg", "회원가입실패");
				//return "common/errorPage";	
				mv.addObject("msg","회원가입실패");
				mv.setViewName("common/errorPage");
			}
			
		} catch(Exception e) {
			//model.addAttribute("msg",e.getMessage());
			//return "common/errorPage";
			mv.addObject("msg",e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST) // 설정한 곳에서 요청이 올경우 반응할 메소드 작성하는 과정임
	public ModelAndView memberLogin(
			@RequestParam("member-id") String memberId,
			@RequestParam("member-pwd") String memberPwd,
			//Model model,
			ModelAndView mv,
			HttpServletRequest request
			) {
		try {
			Member member = new Member(memberId, memberPwd);
			Member loginUser = mService.loginMember(member);
			if(loginUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				//return "redirect:/home.kh";
				mv.setViewName("redirect:/home.kh");
			} else {
				//model.addAttribute("msg","회원정보없음");
				//return "common/errorPage";
				mv.addObject("msg", "회원정보를 찾을 수 없습니다.");
				mv.setViewName("common/errorPage");
			}
			
		} catch(Exception e) {
			
			// request.setAttribute("msg","실패");
			//model.addAttribute("msg", e.getMessage());
			mv.addObject("msg", e.toString());
			//return "common/errorPage";
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
	public ModelAndView memberLogout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
			//return "redirect:/home.kh";
			mv.setViewName("redirect:/home.kh");
		}else {
			//model.addAttribute("msg","로그아웃 실패");
			//return "common/errorPage";
			mv.addObject("msg","로그아웃 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/member/myPage.kh", method=RequestMethod.GET)
	public ModelAndView showMyPage(HttpServletRequest request, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginUser");
			String memberId = member.getMemberId();
			Member mOne = mService.printOneById(memberId);
			String mAddr = mOne.getMemberAddr();
			String [] addrInfos = mAddr.split(",");
			//model.addAttribute("member",mOne);
			//model.addAttribute("addrInfos",addrInfos);
			mv.addObject("member",mOne);
			mv.addObject("addrInfos",addrInfos);
			//return "member/myPage";
			mv.setViewName("member/myPage");
		}catch(Exception e){
			//model.addAttribute("msg", e.getMessage());
			//return "common/errorPage";
			mv.addObject("msg",e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/member/modify.kh", method=RequestMethod.POST)
	public ModelAndView modifyMember(
			@ModelAttribute Member member,
			@RequestParam("post") String post,
			@RequestParam("address") String memberAddr,
			ModelAndView mv) {
		try {
			member.setMemberAddr(post + "," + memberAddr);
			int result = mService.modifyMember(member);
			if(result > 0) {
				//return "redirect:/home.kh";
				mv.setViewName("redirect:/home.kh");
			}else {
				//model.addAttribute("msg", "회원 정보가 수정되지 않았습니다.");
				//return "common/errorPage";
				mv.addObject("msg","회원정보가 수정되지 않았습니다.");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			//model.addAttribute("msg", e.getMessage());
			//return "common/errorPage";
			mv.addObject("msg",e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/member/remove.kh", method=RequestMethod.GET)
	public ModelAndView removeMember(HttpSession session, ModelAndView mv) {
		try {
			Member member = (Member)session.getAttribute("loginUser");
			String memberId = member.getMemberId();
			int result = mService.removeMember(memberId);
			//return "redirect:/member/logout.kh"; // 바로 로그아웃시킴
			mv.setViewName("redirect:/member/logout.kh");
		
		}catch(Exception e) {
			//return "common/errorPage";
			mv.addObject("msg",e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/checkId.kh", method=RequestMethod.GET)
	public String checkId(@RequestParam("memberId") String memberId) {
		Member member = mService.printOneById(memberId);
		if(member == null) {
			System.out.println("ok!");
			return "itsOk";
		}else {
			System.out.println("no!");
			return "itsNotOk";
		}
	}
}
