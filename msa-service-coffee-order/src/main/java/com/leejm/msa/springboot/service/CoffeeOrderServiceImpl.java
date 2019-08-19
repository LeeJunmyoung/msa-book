package com.leejm.msa.springboot.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.leejm.msa.domain.repository.ICoffeeOrderRepository;
import com.leejm.msa.domain.service.CoffeeOrder;

@Service
@Transactional
public class CoffeeOrderServiceImpl extends CoffeeOrder {

	public CoffeeOrderServiceImpl(ICoffeeOrderRepository iCoffeeOrderRepository) {
		super(iCoffeeOrderRepository);
	}

	

}
