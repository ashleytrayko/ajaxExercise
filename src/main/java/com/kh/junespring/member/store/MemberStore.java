package com.kh.junespring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.junespring.member.domain.Member;

public interface MemberStore {
	// selectLoginMember
	public Member selectLoginMember(SqlSession session, Member member);
	// selectOneById
	public Member selectOneById(SqlSession session, String memberId);
	// insertMember
	public int insertMember(SqlSession session, Member member);
	// updateMember
	public int updateMember(SqlSession session, Member member);
	// deleteMember
	public int deleteMember(SqlSession session, String memberId);
	// checkIdDuplicate
	public int checkDupId(SqlSession session, String memberId);
}
