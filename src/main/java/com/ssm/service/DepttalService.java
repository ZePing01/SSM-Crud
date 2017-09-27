package com.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.bean.Depttal;
import com.ssm.dao.DepttalMapper;

@Service
public class DepttalService {

	@Autowired
	private DepttalMapper depttalMapper;
	
	
	public List<Depttal> getDepts() {
		
		List<Depttal> list = depttalMapper.selectByExample(null);
		
		return list;
	}

}
