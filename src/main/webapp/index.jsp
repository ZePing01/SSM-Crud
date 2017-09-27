<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("SSM_PATH",request.getContextPath());
%>
<!-- 引入jquery -->
<script type="text/javascript" src="${SSM_PATH}/static/js/jquery-1.12.4.min.js"></script>
<!-- Bootstrap -->
<link href="${SSM_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${SSM_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	
	<!--员工修改 -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">员工修改</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			     <p class="form-control-static" id="empName_update_static"></p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="email_update" placeholder="email@ssm.com">
			      <span id="helpBlock2" class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
			    <div class="col-sm-10">
			    	<label class="radio-inline">
						<input type="radio" name="gender" id="gender1_update" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  	<input type="radio" name="gender" id="gender2_update" value="W"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">所在部门</label>
			    <div class="col-sm-4">
			    	<select class="form-control" name="dId" id="dept_update_select">
					</select>
			    </div>
			  </div>
			  
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- update end -->
	
	<!--员工添加 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="empName" id="empName_add" placeholder="empName">
			      <span id="helpBlock2" class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="email_add" placeholder="email@ssm.com">
			      <span id="helpBlock2" class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
			    <div class="col-sm-10">
			    	<label class="radio-inline">
						<input type="radio" name="gender" id="gender1_add" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  	<input type="radio" name="gender" id="gender2_add" value="W"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">所在部门</label>
			    <div class="col-sm-4">
			    	<select class="form-control" name="dId" id="dept_add_select">
					</select>
			    </div>
			  </div>
			  
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>



	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
			<h2>SSM-CRUD</h2>
			</div>		
		</div>
		
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-primary btn-sm" id="emp_add_modal_btn">新增</button>				
				<button type="button" class="btn btn-danger btn-sm" id="emp_del_modal_btn">删除</button>				
			</div>
		</div>
		
		<!-- 显示表格数据 -->
		<div class="row">
			<div class=col-md-12>
				<table class="table table-hover" id="emps_tables">
					<thead>
					<tr>
						<th>
							<input type="checkbox" id="check_all"/>
						</th>
						<th>#</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>所在部门</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
					
				</table>
			</div>
		</div>
		
		<!-- 分页 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info">
			</div>
			
			<!-- 分页条 -->
			<div class="col-md-6" id="page_nav">
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var totalRecord,currentPage;
		//页面加载完成以后，直接去发送ajax请求，接收到分页数据
		$(function(){
			to_page(1);
		});		
		
		function to_page(pn) {
			$.ajax({
				url:"${SSM_PATH}/emps",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//3.解析显示分页条
					build_page_nav(result);
				}
			});
		}
		
		function build_emps_table(result){
			$("#emps_tables tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				var checkBokTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.empId); 
				var empNameTd = $("<td></td>").append(item.empName); 
				var genderTd = $("<td></td>").append(item.gender=="M"?"男":"女"); 
				var emailTd = $("<td></td>").append(item.email); 
				var deptNameTd = $("<td></td>").append(item.depttal.deptName);
				
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//自定义属性
				editBtn.attr("edit_id",item.empId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//自定义属性
				delBtn.attr("del_id",item.empId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				
				$("<tr></tr>").append(checkBokTd)
					.append(empIdTd)
					.append(empNameTd)
					.append(genderTd)
					.append(emailTd)
					.append(deptNameTd)
					.append(btnTd)
					.appendTo("#emps_tables tbody");
			});
		}
		
		//解析显示分页信息
		function build_page_info(result){
			$("#page_info").empty();
			$("#page_info").append("第"+result.extend.pageInfo.pageNum+"页,共"
					+result.extend.pageInfo.pages+"页,共  "
					+result.extend.pageInfo.total+"条记录");
			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}
		//解析显示分页条
		function build_page_nav(result){
			$("#page_nav").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if(result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else {
				//首页
				firstPageLi.click(function(){
					to_page(1);
				});
				//前一页
				prePageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}
			
			
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
			
			if(result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else {
				//后一页
				nextPageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				//尾页
				lastPageLi.click(function(){
					to_page(result.extend.pageInfo.pages);
				});
			}
			
			
			
			ul.append(firstPageLi).append(prePageLi);
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function(){
					to_page(item);
				});
				ul.append(numLi);
			});
			ul.append(nextPageLi).append(lastPageLi);
			
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav");
		}
		
		//清除表格、样式数据 
		function reset_form(ele) {
			$(ele)[0].reset();
			//清空表单样式
			$(ele).find("*").removeClass("has-success has-error");
			$(ele).find(".help-block").text("");
		}
		
		//点击新增按钮弹出模态框
		$("#emp_add_modal_btn").click(function(){
			//清除表格、样式数据 
			reset_form("#empAddModal form");
			
			//发送ajax，查询部门信息
			getDepts("#empAddModal select");
			//弹出模态框
			$('#empAddModal').modal({
				backdrop:"static"		
			});
		});
		//查询部门信息
		function getDepts(ele) {
			//清空之前下拉列表
			$(ele).empty();
			$.ajax({
				url:"${SSM_PATH}/depts",
				type:"GET",
				success:function(result){
					//console.log(result);
					//$("#dept_add_select")
					$.each(result.extend.depts,function() {
						var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
						optionEle.appendTo(ele);
					});
				}
				
			})
		}
		
		//校验数据
		function validate_add_form() {
			//1. 校验员工姓名
			var empName = $("#empName_add").val();
			var regName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(empName)) {
				//alert("姓名格式为2-5位字符或3-16位数字和字母组合");
				show_validate_msg("#empName_add", "error", "姓名格式为2-5位字符或3-16位数字和字母组合");
				return false;
			}else {
				show_validate_msg("#empName_add", "success", "");
			}
			
			
			//2. 校验email
			var email = $("#email_add").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)) {
				//alert("邮箱格式错误");
				show_validate_msg("#email_add", "error", "邮箱格式错误");
				return false;
			}else {
				show_validate_msg("#email_add", "success", "");
			}
			return true;
		}
		
		//显示验证信息
		function show_validate_msg(ele,status,msg) {
			//清楚当前元素校验状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if("success" == status) {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else if("error" == status) {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		
		//校验用户名是否可用
		$("#empName_add").change(function(){
			//发送ajax请求验证姓名是否可用
			var empName = this.value;
			$.ajax({
				url:"${SSM_PATH}/checkUser",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					if(result.code == 100) {
						show_validate_msg("#empName_add", "success", "用户名可用");
						$("#emp_save_btn").attr("ajax-val","success");
					}else {
						show_validate_msg("#empName_add", "error", result.extend.va_msg);
						$("#emp_save_btn").attr("ajax-val","error");
					}
				}
			});
		});
		
		//保存
		$("#emp_save_btn").click(function(){
			//1. 对提交给服务器的数据进行校验-前端校验
			if(!validate_add_form()) {
				return false;
			}
			
			if($(this).attr("ajax-val") =="error"){
				return false;
			}
			
			//2. 发送ajax请求
			$.ajax({
				url:"${SSM_PATH}/emp",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					alert(result.msg);
					if(result.code == 100) {
					    //alert(2222222221)
						//保存成功，关闭模态框，跳转到数据最后一页
						$("#empAddModal").modal('hide');
						
						to_page(totalRecord);
					}else {
						//显示失败信息
						//有哪个字段错误信息输出哪一个
						if(undefined != result.extend.errorFields.email) {
							show_validate_msg("#email_add", "error", result.extend.errorFields.email);
						}
						if(undefined != result.extend.errorFields.empName) {
							show_validate_msg("#empName_add", "error", result.extend.errorFields.empName);
						}
						 //alter(result.extend.errorFields.email);
						 //alter(result.extend.errorFields.empName);
						
					}
				}
			});
		});
		
		//update
		//修改-绑定事件  
		$(document).on("click",".edit_btn",function() {
			
			//发送ajax，查询部门信息
			getDepts("#empUpdateModal select");
			//查询当前信息
			getEmp($(this).attr("edit_id"));
			//弹出模态框
			//把员工ID传递给模态框
			$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
			$('#empUpdateModal').modal({
				backdrop:"static"		
			});
		});
		
		function getEmp(id){
			$.ajax({
				url:"${SSM_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					//console.log(result);
					var empData = result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update").val(empData.email);
					$("#empUpdateModal input[name=gender]").val([empData.gender]);
					$("#empUpdateModal select").val([empData.dId]);
				}
			});
		}
		
		//点击更新按钮，更新数据
		$("#emp_update_btn").click(function(){
			//验证邮箱是否合法
			var email = $("#email_update").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)) {
				show_validate_msg("#email_update", "error", "邮箱格式错误");
				return false;
			}else {
				show_validate_msg("#email_update", "success", "");
			}
			//发送ajax请求保存更新数据
			$.ajax({
				url:"${SSM_PATH}/emp/"+$(this).attr("edit_id"),
				type:"PUT",
				//serialize() 序列化
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					//成功后 关闭模态框
					$("#empUpdateModal").modal("hide");
					//到修改对应数据页数
					to_page(currentPage);
				}	
			});
			
		});
		
		//删除-绑定事件
		$(document).on("click",".del_btn",function() {
			//弹出删除确认框
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			var empId = $(this).attr("del_id");
			
			if(confirm("确认删除["+empName+"]吗？")){
				//确认，发送ajax请求
				$.ajax({
					url:"${SSM_PATH}/empt/"+empId,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到删除页
						to_page(currentPage);
					}					
				});
			}
			
		});
		
		
		//全选,反选
		$("#check_all").click(function(){
			//当前选中状态
			$(this).prop("checked");
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		$(document).on("click",".check_item",function() {
			var flag = $(".check_item:checked").length==$(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		$("#emp_del_modal_btn").click(function(){
			
			var empNames = "";
			var del_ids = "";
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
				del_ids += $(this).parents("tr").find("td:eq(1)").text()+"-";
			});
			//去除多余逗号 substring截取
			empNames = empNames.substring(0,empNames.length-1);
			del_ids = del_ids.substring(0,del_ids.length-1);
			
			if(confirm("确认删除["+empNames+"]吗？")){
				$.ajax({
					url:"${SSM_PATH}/emp/"+del_ids,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到删除页
						to_page(currentPage);
					}					
				});
			}
		});
	</script>	
</body>
</html>