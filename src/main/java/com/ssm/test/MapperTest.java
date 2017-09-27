package com.ssm.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.bean.Depttal;
import com.ssm.bean.Emptal;
import com.ssm.dao.DepttalMapper;
import com.ssm.dao.EmptalMapper;

/**
 * 测试dao层
 * @author chensl
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	//推荐spring的项目可以使用spring的单元测试，可以自动注入我们需要的组件
	//1. 导入springTest模块
	//2. @ContextConfiguration指定spring配置文件的位置
	//3. 直接AutoWrite要使用的组建即可
	
	@Autowired
	DepttalMapper depttalMapper;
	
	@Autowired
	EmptalMapper emptalMapper; 
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		
		//1. 插入部门 和 员工
		//depttalMapper.insertSelective(new Depttal(null,"技术部"));
		//depttalMapper.insertSelective(new Depttal(null,"行政部"));
		
		//emptalMapper.insertSelective(new Emptal(null, "tom", "M", "tom@.com", 1));
		//emptalMapper.insertSelective(new Emptal(null, "alli", "W", "alli@.com", 2));
		
		//批量插入 测试数据
		/*for() {
			
		}*/

		EmptalMapper mapper = sqlSession.getMapper(EmptalMapper.class);
		for (int i = 0; i < 10; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 4)+i;
			mapper.insertSelective(new Emptal(null, uid, "W", uid+"@ssm.com", 2));
		}
		
		System.out.println("完成导入");
		
		
		
		
		
		/*//1. 创建springIOC容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2. 从容器中获取mapper
		DepttalMapper bean = ioc.getBean(DepttalMapper.class);*/
	}
	
	
	
}
