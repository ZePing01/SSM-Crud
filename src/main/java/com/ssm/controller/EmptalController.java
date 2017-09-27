package com.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Emptal;
import com.ssm.bean.Msg;
import com.ssm.service.EmptalService;


/**
 * 对员工的CRUD请求
 * @author chensl
 */

@Controller
public class EmptalController {

	@Autowired
	EmptalService emptalService;
	
	/**
	 * 批量删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteAll(@PathVariable("ids")String ids){
		//System.out.println("11111111111111111111");
		
		if(ids.contains("-")){
			List<Integer> del_ids=new ArrayList<Integer>();
		    String[] str_ids=ids.split("-");
		    for(String string:str_ids){
		    	del_ids.add(Integer.parseInt(string));
		    }
		    emptalService.delAll(del_ids);
		}else{
			Integer id=Integer.parseInt(ids);
			emptalService.delEmpById(id);
		}
		return Msg.success();
		
	}
	
	/**
	 * 员工删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/empt/{empId}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("empId")Integer id){
		//System.out.println("11111111111111111111");
		emptalService.delEmpById(id);
		return Msg.success();
		
	}
	
	/**
	 * 员工更新
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	//@Valid 表示数据需要校验
	public Msg updateEmp(Emptal emptal,HttpServletRequest request){
		System.out.println(request.getParameter("gender"));
		System.out.println("将要更新的员工"+emptal);
		emptalService.updateEmp(emptal);
		return Msg.success();
		
	}
	
	/**
	 * 根据id查询员工
  	 */
	@RequestMapping(value="emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	//从地址从取出id
	public Msg getMap(@PathVariable("id")Integer id){
		Emptal emptal=emptalService.getEmp(id);
        return Msg.success().add("emp",emptal);
	}
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping("checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName")String empName){
		//先判断用户名是否是合法的表达式
		String regx="(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        //不匹配
		if(!empName.matches(regx)){
        	return Msg.fail().add("va_msg","后端——用户名必须是2-5为中文或者3-16位英文和字母的组合");
        }
		
		//用户名重复校验
		boolean b=emptalService.checkUser(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "后端——用户名重复");
		}
		
	}
	
	/**
	 * 员工保存
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	//@Valid 表示数据需要校验
	public Msg saveEmp(@Valid Emptal emptal,BindingResult result){
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String,Object> map=new HashMap<String, Object>();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError fieldError:errors){
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误的信息："+fieldError.getDefaultMessage());
			    map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields",map);
		}else{
			emptalService.saveEmp(emptal);
			return Msg.success();
		}
	}
	
	/**
	 * ResponseBody要能正常工作，需要导入jackson包
	 * @param pn
	 * @return
	 */
	//ResponseBody自动将返回的类型转换为json字符串
	@ResponseBody
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn){
		PageHelper.startPage(pn,5);
		//startPage后面紧跟着的一个查询，就是一个分页查询
		List<Emptal> emps = emptalService.getAll();
		//用PageInfo对结果进行包装,连续传入5页
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 查询员工数据（分页查询）
	 * @return
	 */
	
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,
			Model model) {
		
		//引入pageHelper分页插件
		//在查询之前只需要调用，传入页码，以及每页显示数量
		PageHelper.startPage(pn,5);
		//startPage后面紧跟着的一个查询，就是一个分页查询
		List<Emptal> emps = emptalService.getAll();
		//用PageInfo对结果进行包装,连续传入5页
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		
		return "list";
	}	
}
