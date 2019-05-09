package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.vo.OrderInfo;

public interface OrderDAO {
	/**
	 * 주문하기
	 * @param info 주문객체-id, 상품, 수량정보가 반드시 필요하다
	 * @throws DataAccessException - 주문실패(주문자ID가 없는 경우, 상품정보가 없는 경우, 수량정보가 없는 경우)시 발생한다
	 */
	void insertOrder(OrderInfo info) throws AddException;
	List<OrderInfo> selectAll();
	List<OrderInfo> selectById(String id);
}
