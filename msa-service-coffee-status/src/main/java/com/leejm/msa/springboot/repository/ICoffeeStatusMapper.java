package com.leejm.msa.springboot.repository;



import org.apache.ibatis.annotations.Mapper;

import com.leejm.msa.springboot.repository.dvo.OrderStatusDVO;

@Mapper
public interface ICoffeeStatusMapper {
	int insertCoffeeOrderStatus(OrderStatusDVO orderStatusDVO);
	OrderStatusDVO selectCoffeeOrderStatus();
	int createStatusTable();
}