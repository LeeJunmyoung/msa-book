package com.leejm.msa.domain.repository;

import com.leejm.msa.domain.model.OrderEntity;

public interface ICoffeeOrderRepository {

	public String coffeeOrderSave(OrderEntity orderEntity);
	
}
