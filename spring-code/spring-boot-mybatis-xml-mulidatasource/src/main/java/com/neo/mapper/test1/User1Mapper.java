package com.neo.mapper.test1;

import com.neo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface User1Mapper {
	
	List<User> getAll();
	
	User getOne(@Param("id")Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}