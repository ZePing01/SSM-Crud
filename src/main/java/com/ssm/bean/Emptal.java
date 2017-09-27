package com.ssm.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class Emptal {
    private Integer empId;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})"
    		,message="姓名格式为2-5位字符或3-16位数字和字母组合")
    private String empName;

    private String gender;

    //@Email 可以只写这个
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$"
    		,message="邮箱格式错误")
    private String email;

    private Integer dId;
    
    private Depttal depttal;

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

	public Depttal getDepttal() {
		return depttal;
	}

	public void setDepttal(Depttal depttal) {
		this.depttal = depttal;
	}

    //无参
    public Emptal() {
		super();
	}
    //有参
	public Emptal(Integer empId, String empName, String gender, String email,
			Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}

	@Override
	public String toString() {
		return "Emptal [empId=" + empId + ", empName=" + empName + ", gender="
				+ gender + ", email=" + email + ", dId=" + dId + ", depttal="
				+ depttal + "]";
	}
    
	
}