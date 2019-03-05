package com.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.entity.Review;
import com.news.entity.User;
import com.news.service.ReviewService;
import com.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
/**
 *
 *实现后台对用户的查看和删除
 */
public class UserController {

	@Autowired
    UserService userService;
	@Autowired
    ReviewService reviewService;

	@RequestMapping("showusers")
	public ModelAndView showUsers() {
		ModelAndView mv = new ModelAndView("houtai/show.jsp");
		List<User> users = userService.findAll();
		for (int i = 0; i < users.size(); i++) {
			System.out.println("=========" + users.get(i).getUname());
		}
		mv.addObject("data", users);
		System.out.println(users);
		return mv;
	}

	@RequestMapping("remove")
	public ModelAndView delete(Integer uid) {
		ModelAndView mv = new ModelAndView("error");
		Integer i = userService.removeUser(uid);
		if (i != 0) {
			return showUsers();
		} else {
			return mv;
		}

	}

	@RequestMapping("delUser")
	public String deleteUser(HttpServletRequest req) {
		Integer mid = Integer.parseInt(req.getParameter("id"));
		List<Review> ReviewList = reviewService.selectByuid(mid);
		if (ReviewList.isEmpty()) {
			int i = userService.removeUser(mid);
		}
		return "redirect:UserInfo";
	}

	// 分页查询用户
	@RequestMapping("UserInfo")
	public String getUsers(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 从第一条开始 每页查询五条数据
		PageHelper.startPage(pn, 5);
		List<User> users = userService.findAll();
		// 将用户信息放入PageInfo对象里
		PageInfo page = new PageInfo(users, 5);
		model.addAttribute("pageInfo", page);
		return "houtai/show.jsp";
	}

	// 返回后台首页
	@RequestMapping("index")
	public String index() {
		return "redirect:houtai/index.jsp";
	}
}
