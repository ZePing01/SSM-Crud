package com.ssm.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.ssm.bean.Emptal;

/**
 * 使用spring测试模块提供的测试功能请求，测试crud的正确性
 * @author chensl
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

	//传入springmvc的ioc
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求，获取处理结果
	MockMvc mockMvc;
	
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5"))
			.andReturn();
		
		//请求成功以后，请求域中会有pageInfo，可以取出pageInfo来进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		
		System.out.println("当前页码："+ pi.getPageNum());
		System.out.println("总页数："+ pi.getPages());
		System.out.println("总记录数："+ pi.getTotal());
		System.out.println("连续显示的页码：");
		//getNavigatepageNums()得到所有的导航页码
		int[] nums = pi.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(" " + i);
		}
		//获取员工数据
		List<Emptal> list = pi.getList();
		for (Emptal emptal : list) {
			System.out.println("id:"+emptal.getEmpId()+"--name:"+emptal.getEmpName());
		}
		
	}
	
	
}