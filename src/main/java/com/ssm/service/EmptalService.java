package com.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.bean.Emptal;
import com.ssm.bean.EmptalExample;
import com.ssm.bean.EmptalExample.Criteria;
import com.ssm.dao.EmptalMapper;

@Service
public class EmptalService {

	@Autowired
	EmptalMapper emptalMapper;
	
	/**
	 * 查询所有员工
	 * @return list
	 */
	public List<Emptal> getAll() {
		return emptalMapper.selectByExampleWithDept(null);
	}

	/**
	 * 保存
	 * @param emptal
	 */
	public void saveEmp(Emptal emptal) {
		emptalMapper.insertSelective(emptal);
		
	}

	/**
	 * 检验用户名是否可用
	 * @param empName
	 * @return true == 0
	 */
	public boolean checkUser(String empName) {
		
		EmptalExample example = new EmptalExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = emptalMapper.countByExample(example);
		return count == 0;
	}
	
	//根据员工ID查询数据
	public Emptal getEmp(Integer id) {
		Emptal emptal = emptalMapper.selectByPrimaryKey(id);
		return emptal;
	}

	/**
	 * 更新-保存
	 * @param emptal
	 */
	public void updateEmp(Emptal emptal) {
		emptalMapper.updateByPrimaryKeySelective(emptal);
	}

	//删除单挑数据
	public void delEmpById(Integer id) {

		emptalMapper.deleteByPrimaryKey(id);
		
	}

	//删除多数据
	public void delAll(List<Integer> ids) {
		EmptalExample example = new EmptalExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		emptalMapper.deleteByExample(example);
	}

}
