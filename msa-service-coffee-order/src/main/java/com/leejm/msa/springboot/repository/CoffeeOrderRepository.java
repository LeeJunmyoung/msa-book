package com.leejm.msa.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leejm.msa.domain.model.OrderEntity;
import com.leejm.msa.domain.repository.ICoffeeOrderRepository;
import com.leejm.msa.springboot.repository.jpa.ICoffeeOrderJpaRepository;
import com.leejm.msa.springboot.repository.jpa.OrderEntityJPO;

@Repository
public class CoffeeOrderRepository implements ICoffeeOrderRepository {

	@Autowired
	private ICoffeeOrderJpaRepository iCoffeeOrderJpaRepository;
	
	@Override
	public String coffeeOrderSave(OrderEntity orderEntity) {
		
		OrderEntityJPO orderEntityJPO = new OrderEntityJPO();
		orderEntityJPO.setOrderNumber(orderEntity.getOrderNumber());
		orderEntityJPO.setCoffeeName(orderEntity.getCoffeeName());
		orderEntityJPO.setCoffeeCount(orderEntity.getCoffeeCount());
		orderEntityJPO.setCustomerName(orderEntity.getCustomerName());
		
		iCoffeeOrderJpaRepository.save(orderEntityJPO);
		
		return orderEntityJPO.getId();
		
	}

}
