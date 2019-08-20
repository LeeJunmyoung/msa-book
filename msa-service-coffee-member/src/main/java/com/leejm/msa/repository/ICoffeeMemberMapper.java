package com.leejm.msa.repository;

import org.apache.ibatis.annotations.Mapper;

import com.leejm.msa.repository.dvo.MemberDVO;

@Mapper
public interface ICoffeeMemberMapper {
	
	MemberDVO existsByMemberName(MemberDVO memberDVO);
	int createMemberTable();
	int insertMemberData();
	
}