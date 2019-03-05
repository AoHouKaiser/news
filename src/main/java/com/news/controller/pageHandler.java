package com.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.dao.NewsMapper;
import com.news.entity.News;
import com.news.service.getNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 *  Limitlist方法，利用MyBatis的pagehelper插件结合xml注入条件查询语句实现多条件+分页查询
 * 
 */
@Controller
public class pageHandler {
	@Autowired
    getNews getnews; // 获取到一个news的service
	@Resource
    NewsMapper newsMapper;

	@RequestMapping("queryAllItems")
	//该方法实现了新闻分类标题概览的分类显示功能
	public ModelAndView Limitlist(HttpServletRequest request) {
		String ntId=request.getParameter("ntid");
		System.out.println("===NTID====="+ntId);
		//request.getSession().setAttribute("ntid", Integer.parseInt(ntId));
		request.getSession().setAttribute("ntid", Integer.parseInt(ntId));
		int pageNum = 1;
		if (request.getParameter("pageNum") == null
				|| "".equals(request.getParameter("page"))) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		ModelAndView modelAndView = new ModelAndView();
		
		Integer ntid=(Integer)request.getAttribute("ntid");
		List<News> listByNtid = newsMapper.listByNtid(ntid);// 首先进行条件查询，返回一个news的list
		List<News> listByNtid1 = getnews.list(pageNum, listByNtid).getList();// 对该条件查询返回一个分页后的list链表
		// List<News> listByNtid1=getnews.list(pageNum,listByNtid);
		pageNum = getnews.list(pageNum, listByNtid).getPageNum();
		System.out.println(pageNum + "=1111===page");
		int totalPage1 = getnews.list(pageNum, listByNtid).getPages();
		System.out.println(pageNum + "==================");
		System.out.println(totalPage1 + "==================");
		request.getSession().setAttribute("pageNum", pageNum);// 在session对象中传递当前页数
		request.getSession().setAttribute("totalPage", totalPage1);// 总页数
		modelAndView.addObject("itemsList", listByNtid1);// 放入到模型对象中
		modelAndView.setViewName("queryAllItems.jsp");// 进行jsp页面的跳转

		return modelAndView;
	}
	
	@RequestMapping("searchByTitle")
	//该方法实现了新闻按标题查询概览的显示功能
	public ModelAndView searchByTitle(HttpServletRequest request) {
		boolean read=false;
		if(read==false){String title=request.getParameter("title");
		request.getSession().setAttribute("title", title);
		read=true;
		}
	
		int pageNum = 1;
		if (request.getParameter("pageNum") == null
				|| "".equals(request.getParameter("page"))) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		ModelAndView modelAndView = new ModelAndView();
		
		String title=(String)request.getSession().getAttribute("title");
		System.out.println("======"+title+"======");
		List<News> listByNtid = newsMapper.listBysearchTitle(title);// 首先进行条件查询，返回一个news的list
		for(News n:listByNtid){
			System.out.println("==="+n.getTitle()+"===");
		}
		List<News> listByNtid1 = getnews.list(pageNum, listByNtid).getList();// 对该条件查询返回一个分页后的list链表
		pageNum = getnews.list(pageNum, listByNtid).getPageNum();
		int totalPage1 = getnews.list(pageNum, listByNtid).getPages();
		request.getSession().setAttribute("pageNum", pageNum);// 在session对象中传递当前页数
		request.getSession().setAttribute("totalPage", totalPage1);// 总页数
		modelAndView.addObject("itemsList", listByNtid1);//放入到模型对象中
		modelAndView.setViewName("searchByTitle.jsp");// 进行jsp页面的跳转

		return modelAndView;
		
		
	}
	
	@RequestMapping("searchByTitle1")
	//该方法实现了新闻按标题查询概览的显示功能
	public String  searchByTitle1(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn,
            Model model, HttpServletRequest request){
		// 从第一条开始 每页查询五条数据
		PageHelper.startPage(pn, 5);
		String title=request.getParameter("title");
		request.setAttribute("title", title);
		ModelAndView modelAndView = new ModelAndView();
		List<News> list = newsMapper.listBysearchTitle(title.replace("'", ""));// 首先进行条件查询，返回一个news的list
		PageInfo page = new PageInfo(list, 5);
		model.addAttribute("pageInfo", page);
		return "searchByTitle1.jsp";

		
		
		
		
	}
	
	
	// 分页查询新闻
	@RequestMapping("NewsInfo")
	public String NewsInfo(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn,    //利用注解来获取页面post的属性
            Model model, HttpServletRequest req) {
		// 从第一条开始 每页查询五条数据
		PageHelper.startPage(pn, 5);
		String ntid=null,ntid1 = null;
		ntid=req.getParameter("ntid");
		req.setAttribute("ntid", ntid);
		
		 List<News> listByNtid = newsMapper.listByNtid(Integer.parseInt(ntid.replace("'", "")));// 首先进行条件查询，返回一个news的list
		// 将用户信息放入PageInfo对象里
		PageInfo page = new PageInfo(listByNtid, 5);
		model.addAttribute("pageInfo", page);
		return "queryAllItems1.jsp";
	}
	

	// 返回后台首页
	@RequestMapping("home")
	public String index() {
		/*return "redirect:index.jsp";*/
		return "chushihuanewstype";
	}
	
}
