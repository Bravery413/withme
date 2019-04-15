package controllers;

import com.alibaba.fastjson.JSON;

import models.User;
import models.UserDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserDetailService;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



@Controller
public class LoginController {
	
	@Resource private UserService userService;
	@Resource private UserDetailService userDetailService;
	
	@RequestMapping(value="/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doLogin(String userName,String userPassword,HttpSession httpSession){
		String result="fail";
		User user = userService.getUser(userName);
		UserDetail userDetail = userDetailService.getUserDetail(userName);
		if(user!=null){
			if(Objects.equals(userDetail.getUserDetailPassword(), userPassword)){
				httpSession.setAttribute("currentUser",user);
				result = "success";
			}
			else{
				result = "wrong";
			}
		}
		else{
			result = "unexist";
		}
		Map<String, Object> results = new HashMap<String,Object>();
		results.put("result", result);
		if(result.equals("success"))
			results.put("user", JSON.toJSON(user));
		return results;
	}
	
	
	@RequestMapping(value="/doLogout")
	public String doLogout(HttpSession httpSession){
		httpSession.removeAttribute("currentUser");
		return "redirect:login";
	}
}