package com.leejm.msa.springboot.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leejm.msa.domain.model.CoffeeOrderCVO;
import com.leejm.msa.springboot.messageq.KafkaProducer;
import com.leejm.msa.springboot.service.CoffeeOrderServiceImpl;
import com.leejm.msa.springboot.service.IMsaServiceCoffeeMember;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CoffeeOrderRestController {

	@Autowired
	private CoffeeOrderServiceImpl coffeeOrderServiceImpl;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private IMsaServiceCoffeeMember iMsaServiceCoffeeMember;
	
	@HystrixCommand
	@RequestMapping(value="/coffeeOrder", method = RequestMethod.POST)
	public ResponseEntity<CoffeeOrderCVO> coffeeOrder(@RequestBody CoffeeOrderCVO coffeeOrderCVO) {
		
		if(iMsaServiceCoffeeMember.coffeeMember(coffeeOrderCVO.getCustomerName())) {
			System.out.println(coffeeOrderCVO.getCustomerName() + " is a member");
		}
		
		coffeeOrderServiceImpl.coffeeOrder(coffeeOrderCVO);
		
		kafkaProducer.send("kafka-test", coffeeOrderCVO);
		
		return new ResponseEntity<CoffeeOrderCVO>(coffeeOrderCVO, HttpStatus.OK);
	}
	
}
