package com.xin.dao;

import com.xin.model.Hobby;
import com.xin.model.HobbyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyMapper {


    int countByExample(HobbyExample example);

    int deleteByExample(HobbyExample example);

    int insert(Hobby record);

    int insertSelective(Hobby record);

    List<Hobby> selectByExample(HobbyExample example);

    int updateByExampleSelective(@Param("record") Hobby record, @Param("example") HobbyExample example);

    int updateByExample(@Param("record") Hobby record, @Param("example") HobbyExample example);
}