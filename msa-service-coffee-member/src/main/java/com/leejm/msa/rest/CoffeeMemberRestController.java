package com.leejm.msa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leejm.msa.repository.ICoffeeMemberMapper;
import com.leejm.msa.repository.dvo.MemberDVO;
import com.leejm.msa.rest.rvo.MemberRVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class CoffeeMemberRestController {
	
	@Autowired
	ICoffeeMemberMapper iCoffeeMemberMapper;
	
	
	/*
	 *	회원 확인  
	 */
	@HystrixCommand(fallbackMethod = "coffeeMemberFallbackFunction")
	@RequestMapping(value = "/coffeeMember/v1.0/{memberName}", method = RequestMethod.GET)
	public String coffeeMember(@PathVariable("memberName") String memberName) throws Exception {
		
		MemberDVO memberDVO = new MemberDVO();
		memberDVO.setMemberName(memberName);
		
		System.out.println("Search : " + memberName);
		
		if(iCoffeeMemberMapper.existsByMemberName(memberDVO).getMemberName() == null) {
			return "false";
		}
		else {
			return "true";
		}
	}
	public String coffeeMemberFallbackFunction(String memberName){
		return memberName + " is null";
	}

	@HystrixCommand
	@RequestMapping(value = "/coffeeMember/v1.1", method = RequestMethod.POST)
	public boolean coffeeMember(@RequestBody MemberRVO memberRVO) {
		
		MemberDVO memberDVO = new MemberDVO();
		memberDVO.setMemberName(memberRVO.getMemberName());
		
		if(iCoffeeMemberMapper.existsByMemberName(memberDVO)
				.getMemberName()
				.isEmpty()) return false;
		else return true;
	}
	
	
	/*
	 *	서킷 브레이커 테스트
	 */
	@HystrixCommand(fallbackMethod = "fallbackFunction")
	@RequestMapping(value = "/fallbackTest", method = RequestMethod.GET)
	public String fallbackTest() throws Throwable{
		throw new Throwable("fallbackTest");
	}
	public String fallbackFunction(){
		return "fallbackFunction()";
	}
	
	
	/*
	 *	테스트 테이블 & 데이터 생성.
	 */
	@RequestMapping(value = "/createMemberTable", method = RequestMethod.GET)
	public void createMemberTable() {
		iCoffeeMemberMapper.createMemberTable();
	}
	
	@RequestMapping(value = "/insertMemberData", method = RequestMethod.GET)
	public void insertMemberData() {
		iCoffeeMemberMapper.insertMemberData();
	}
}