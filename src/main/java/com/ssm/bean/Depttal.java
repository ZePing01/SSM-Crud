package com.ssm.bean;

public class Depttal {
    private Integer deptId;

    private String deptName;

    
    
    //无参构造
    public Depttal() {
		super();
	}

	//有参构造
    public Depttal(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}