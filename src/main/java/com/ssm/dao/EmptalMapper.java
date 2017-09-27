package com.ssm.dao;

import com.ssm.bean.Emptal;
import com.ssm.bean.EmptalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmptalMapper {
    long countByExample(EmptalExample example);

    int deleteByExample(EmptalExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Emptal record);

    int insertSelective(Emptal record);

    List<Emptal> selectByExample(EmptalExample example);

    Emptal selectByPrimaryKey(Integer empId);
    
    List<Emptal> selectByExampleWithDept(EmptalExample example);
    
    Emptal selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Emptal record, @Param("example") EmptalExample example);

    int updateByExample(@Param("record") Emptal record, @Param("example") EmptalExample example);

    int updateByPrimaryKeySelective(Emptal record);

    int updateByPrimaryKey(Emptal record);
}