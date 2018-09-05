package com.whiuni.fastshop.dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.UserVO;

public class UserDAO extends SqlSessionDaoSupport{

	private SqlSessionTemplate sqlSessionTemplate;
	//빈에 선언했기 떄문에 아래와 같은 의미가 됨.
	//private SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate();
	
	public void insert(UserVO userVO) {
		getSqlSession().insert("User.insert",userVO);
	}
	

	public List<UserVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("User.selectList",paramMap);
	}
	
	public UserVO select(int id) {
		return getSqlSession().selectOne("User.select",id);
	}

	public UserVO selectByUsername(String username) {
		return getSqlSession().selectOne("User.selectByUsername",username);
	}
	
	public void update(UserVO userVO) {
		getSqlSession().update("User.update", userVO);
	}
	
	public void delete(int id) {
		getSqlSession().delete("User.delete",id);
	}


}
