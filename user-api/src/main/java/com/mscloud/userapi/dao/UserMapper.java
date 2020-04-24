package com.mscloud.userapi.dao;

import com.mscloud.userapi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{
}
