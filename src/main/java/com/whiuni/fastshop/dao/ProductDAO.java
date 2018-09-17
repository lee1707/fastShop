package com.whiuni.fastshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.ProductVO;

public class ProductDAO extends SqlSessionDaoSupport{

	private SqlSessionTemplate sqlSessionTemplate;
	//�� �����߱� ������ �Ʒ��� ���� �ǹ̰� ��.
	//private SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate();
	
	public void insert(ProductVO productVO) {
		getSqlSession().insert("Product.insert",productVO);
	}
	

	public List<ProductVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("Product.selectList",paramMap);
	}
	
	public void update(ProductVO productVO) {
		getSqlSession().update("Product.update", productVO);
	}
	
	public void delete(int id) {
		getSqlSession().delete("Product.delete",id);
	}


	public ProductVO select(int id) {
		return getSqlSession().selectOne("Product.select",id);
	}
}
