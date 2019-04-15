package controllers;


import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
	
	@Autowired private UserService userService;
	
	@RequestMapping(value="/doFindUserByName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserByName(String userName){
		User user = userService.getUser(userName);
		//通过HashMap来构建Json数据，其实和Server里面通过JSONObject来构建效果是一样的
		Map<String,Object> resoult = new HashMap<String,Object>();
		if(user!=null){
			resoult.put("userId", user.getUserId());
			resoult.put("userName", user.getUserName());
			resoult.put("userNickName", user.getUserNickName());
			resoult.put("userIsOnline", user.getUserIsOnline());
			resoult.put("userRole", user.getUserRole());
		}
		else
			resoult=null;
		return resoult;
	};
	
	@RequestMapping(value="/doFindUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserById(int userId){
		User user = userService.getUser(userId);
		Map<String,Object> resoult = new HashMap<String,Object>();
		if(user!=null){
			resoult.put("userId", user.getUserId());
			resoult.put("userName", user.getUserName());
			resoult.put("userNickName", user.getUserNickName());
			resoult.put("userIsOnline", user.getUserIsOnline());
			resoult.put("userRole", user.getUserRole());
		}
		else
			resoult=null;
		return resoult;
	};
	
	
}