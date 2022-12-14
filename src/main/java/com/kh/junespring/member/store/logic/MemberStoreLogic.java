package com.kh.junespring.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.junespring.member.domain.Member;
import com.kh.junespring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore {

	@Override
	public Member selectLoginMember(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.selectLoginOne", member);
		return mOne;
	}

	@Override
	public Member selectOneById(SqlSession session, String memberId) {
		Member mOne = session.selectOne("MemberMapper.selectOneById", memberId);
		return mOne;
	}

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}


	@Override
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateMember",member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		//int result = session.delete("MemberMapper.deleteMember", memberId);
		int result = session.update("MemberMapper.deleteMember",memberId);
		return result;
	}

	@Override
	public int checkDupId(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.checkIdDuplicate", memberId);
		return result;
	}

}
