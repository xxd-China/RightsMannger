package com.xxd.service;

import java.util.List;

import com.xxd.pojo.UserOnline;


public interface SessionService {

	//在線用戶
	List<UserOnline> list();

	//強制退出
	boolean forceLogout(String sessionId);
}
