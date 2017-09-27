package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.bean.Depttal;
import com.ssm.bean.Msg;
import com.ssm.service.DepttalService;

/**
 * 处理和部门有关的请求
 * @author chensl
 *
 */

@Controller
public class DepttalController {

	@Autowired
	private DepttalService depttalService;
	
	/**
	 * 返回所有部门信息
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		//查出的所有部门信息
		List<Depttal> list = depttalService.getDepts();
		//System.out.println("**************************"+list+"******************************");
		return Msg.success().add("depts", list);
	}
	
}
