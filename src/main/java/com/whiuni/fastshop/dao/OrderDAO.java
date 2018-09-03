package com.whiuni.fastshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.OrderVO;

public class OrderDAO extends SqlSessionDaoSupport{

	private SqlSessionTemplate sqlSessionTemplate;
	//빈에 선언했기 떄문에 아래와 같은 의미가 됨.
	//private SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate();
	
	public void insert(OrderVO orderVO) {
		getSqlSession().insert("Order.insert",orderVO);
	}

	public List<OrderVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("Order.selectList",paramMap);
	}
	
	public void update(OrderVO orderVO) {
		getSqlSession().update("Order.update", orderVO);
	}
	
	public void delete(int id) {
		getSqlSession().delete("Order.delete",id);
	}
}
