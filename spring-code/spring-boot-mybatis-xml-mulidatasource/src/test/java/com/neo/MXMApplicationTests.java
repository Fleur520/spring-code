package com.neo;

import com.neo.enums.UserSexEnum;
import com.neo.mapper.test1.User1Mapper;
import com.neo.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MXMApplicationTests {


	@Autowired
	private SqlSessionFactory factory;

	@Test
	public void insert() throws IOException {

		SqlSession sqlSession = factory.openSession();
		User1Mapper user1Mapper = sqlSession.getMapper(User1Mapper.class);


		LocalDate date = LocalDate.now();
		LocalDateTime now = LocalDateTime.now();
		user1Mapper.insert(new User("zm", "a123456", UserSexEnum.MAN ,date ,now));
		System.out.println("数据打印===="+user1Mapper.getAll());
		System.out.println("class===="+user1Mapper.getClass());
	}


	@Test
	public void insert2() throws IOException {

		SqlSession sqlSession = factory.openSession();
		sqlSession.insert("com.neo.mapper.test1.User1Mapper.insert",new User("zm==ddd", "a123456", UserSexEnum.MAN));
		List<Object> objects = sqlSession.selectList("com.neo.mapper.test1.User1Mapper.getAll");
		System.out.println("数据打印===="+objects);
		System.out.println("class===="+objects.getClass());
	}



	@Test
	public void contextLoads() throws IOException {

		SqlSession sqlSession = factory.openSession();

		User1Mapper user1Mapper = sqlSession.getMapper(User1Mapper.class);
		List<User> all = user1Mapper.getAll();
		System.out.println(all);
		System.out.println("hello world");
	}


	@Test
	public void getOne() throws IOException {

		SqlSession sqlSession = factory.openSession();

		User1Mapper user1Mapper = sqlSession.getMapper(User1Mapper.class);
		User one = user1Mapper.getOne(28L);
		System.out.println(one.toString());
		System.out.println("hello world");
	}



}
