package com.kh.junespring.member.service;

import com.kh.junespring.member.domain.Member;

public interface MemberService {
	// loginMember
	public Member loginMember(Member member);
	// printOneById
	public Member printOneById(String memberId);
	// registerMember
	public int registerMember(Member member);
	// modifyMember
	public int modifyMember(Member member);
	// removeMember
	public int removeMember(String memberId);
	// checkDuplicate
	public int checkDupId(String memberId);
}
