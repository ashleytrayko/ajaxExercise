package com.kh.junespring.member.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.junespring.member.domain.Member;
import com.kh.junespring.member.service.MemberService;
import com.kh.junespring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired 
	private SqlSessionTemplate session;
	@Autowired
	private MemberStore mStore;
	
	@Override
	public Member loginMember(Member member) {
		Member mOne = mStore.selectLoginMember(session, member);
		return mOne;
	}

	@Override
	public Member printOneById(String memberId) {
		Member mOne = mStore.selectOneById(session, memberId);
		return mOne;
	}

	@Override
	public int registerMember(Member member) {
		//SqlSession session = SqlSessionTemplate.getSqlsession();
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int modifyMember(Member member) {
		int result = mStore.updateMember(session, member);
		return result;
	}

	@Override
	public int removeMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}


}
