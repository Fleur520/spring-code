package com.neo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.enums.UserSexEnum;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;


	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;


	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public User() {
		super();
	}

	public User(String userName, String passWord, UserSexEnum userSex  ) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}

	public User(String userName, String passWord, UserSexEnum userSex ,LocalDateTime  birthday) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
		this.birthday = birthday;
	}

	public User(String userName, String passWord, UserSexEnum userSex ,LocalDate createDate , LocalDateTime  birthday) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
		this.createDate = createDate;
		this.birthday = birthday;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
	}

}