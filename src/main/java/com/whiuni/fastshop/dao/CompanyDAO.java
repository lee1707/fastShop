package com.whiuni.fastshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.CompanyVO;

public class CompanyDAO extends SqlSessionDaoSupport{

	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(CompanyVO companyVO) {
		getSqlSession().insert("Company.insert",companyVO);
	}

	public List<CompanyVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("Company.selectList",paramMap);
	}
	
	public void update(CompanyVO companyVO) {
		getSqlSession().update("Company.update", companyVO);
	}
	
	public void delete(int id) {
		getSqlSession().delete("Company.delete",id);
	}

	public CompanyVO select(int id) {
		return getSqlSession().selectOne("Company.select",id);
	}
}

