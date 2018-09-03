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
//...?? xml�� �̹� parameterType���� �־��ִµ� userVO�� �־��ִ� ���Ⱑ �� �ʿ�����?
	
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
