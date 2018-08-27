package com.whiuni.fastshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.whiuni.fastshop.vo.UserVO;

public class UserDAO extends SqlSessionDaoSupport{

	private SqlSessionTemplate sqlSessionTemplate;
	//�� �����߱� ������ �Ʒ��� ���� �ǹ̰� ��.
	//private SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate();
	
	public void insert(UserVO userVO) {
		getSqlSession().insert("User.insert",userVO);
	}
	public List<UserVO> selectList(){
		Map<String, String> paramMap = new HashMap<String, String>();
		return getSqlSession().selectList("User.selectList",paramMap);
	}
}
