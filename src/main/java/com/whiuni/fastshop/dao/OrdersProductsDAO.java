package com.whiuni.fastshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.OrdersProductsVO;


public class OrdersProductsDAO extends SqlSessionDaoSupport{
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(OrdersProductsVO ordersProductsVO) {
		getSqlSession().insert("OrdersProducts.insert",ordersProductsVO);
	}
//...?? xml에 이미 parameterType으로 넣어주는데 userVO를 넣어주는 여기가 왜 필요하지?
	
	public List<OrdersProductsVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("OrdersProducts.selectList",paramMap);
	}
	
	public void update(OrdersProductsVO ordersProductsVO) {
		getSqlSession().update("OrdersProducts.update", ordersProductsVO);
	}
	
	public void delete(int id) {
		getSqlSession().delete("OrdersProducts.delete",id);
	}
}
