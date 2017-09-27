package com.ssm.dao;

import com.ssm.bean.Depttal;
import com.ssm.bean.DepttalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepttalMapper {
    long countByExample(DepttalExample example);

    int deleteByExample(DepttalExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(Depttal record);

    int insertSelective(Depttal record);

    List<Depttal> selectByExample(DepttalExample example);

    Depttal selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") Depttal record, @Param("example") DepttalExample example);

    int updateByExample(@Param("record") Depttal record, @Param("example") DepttalExample example);

    int updateByPrimaryKeySelective(Depttal record);

    int updateByPrimaryKey(Depttal record);
}