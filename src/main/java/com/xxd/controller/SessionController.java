package com.xxd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.pojo.ResponseBo;
import com.xxd.pojo.UserOnline;
import com.xxd.service.SessionService;



@Controller
public class SessionController {
	
	@Resource(name="sessionServiceImpl")
	SessionService sessionService;
	
	//获取在线用户信息
	@RequestMapping("session")
	@RequiresPermissions("session:list")
	public String online() {
		return "system/monitor/online";
	}

	
	@ResponseBody
	@RequestMapping("session/list")
	public Map<String, Object> list() {
		List<UserOnline> list = sessionService.list();
		Map<String, Object> rspData = new HashMap<String, Object>();
		rspData.put("rows", list);
		rspData.put("total", list.size());
		return rspData;
	}

	@ResponseBody
	@RequiresPermissions("user:kickout")
	@RequestMapping("session/forceLogout")
	public ResponseBo forceLogout(String id) {
		try {
			sessionService.forceLogout(id);
			return ResponseBo.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("踢出用户失败");
		}

	}
}
