package controllers;

import com.alibaba.fastjson.JSONArray;
import models.User;
import models.UserRelation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserRelationService;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class UserRealtionController {
	
	@Resource private UserService userService;
	@Resource private UserRelationService userRelationService;
	
	@RequestMapping(value="/buildRelation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> buildRelation(int userIdA,int userIdB){
		UserRelation userRelation = new UserRelation();
		userRelation.setUserIdA(userIdA);
		userRelation.setUserIdB(userIdB);
		userRelation.setRelationStatus(1);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		userRelation.setRelationStart(timestamp);
		userRelationService.addUserRelation(userRelation);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", "success");
		return resoult;
	}
	
	@RequestMapping(value="/removeRelation",method=RequestMethod.POST)
	public String removeRelation(int userId,HttpSession httpSession){
		User user = (User)httpSession.getAttribute("currentUser");
		int idA = user.getUserId();
		int idB = userId;
		userRelationService.deleteUserRelation(idA, idB);
		httpSession.setAttribute("friends", userRelationService.getAllFriends(user.getUserId()));
		return "main";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getRelations",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRelations(int userId){
		List<User> list = new ArrayList<User>();
		list = userRelationService.getAllFriends(userId);
		String relations = JSONArray.toJSONString(list);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("relations", relations);
		return resoult;
	}
}