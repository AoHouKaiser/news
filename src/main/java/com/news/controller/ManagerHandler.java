package com.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.entity.Manager;
import com.news.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 *	实现对管理员的管理
 */
@Controller
@RequestMapping("manager")
public class ManagerHandler{
	
	 @Autowired
     ManagerService managerService;


	     // 添加管理员并重定向  
	    @RequestMapping("addManager") 
	    public String addManager(HttpServletRequest req){
	    	String  name=req.getParameter("managerName");
	    	String pwd=req.getParameter("managerPassword");
	    	Manager manager=new Manager();
	    	manager.setMname(name);
	    	manager.setPwd(pwd);
	        if(manager != null){
	        	managerService.saveManager(manager);
	        }
	        return "redirect:ManagerInfo";
	    }

	     
	    
	     // 修改管理员信息
	    @RequestMapping("editManager")  
	    public String editUser(HttpServletRequest req){
	           String id=req.getParameter("editManagerid");
	           String pwd=req.getParameter("editPassword");
	          String name=req.getParameter("editManagername");
	          Manager manager = managerService.findUserById(Integer.parseInt(id));
	       
	          manager.setMname(name);
	          manager.setPwd(pwd);
	          managerService.updateUser(manager);
	          //managerService.deleteManager(Integer.parseInt(id));
	          
	         return "redirect:ManagerInfo";  
	    }

	 
	     // 查询所有管理员
	  
	    @RequestMapping("getAllManager")  
	    public String getAllUser(Model model){
	        List<Manager> manager = managerService.findAll();
	        model.addAttribute("managerList",manager);
	        return "houtai/allManager.jsp";
	    }

	   
	     // 查询单个管理员
	   
	    @RequestMapping("getManager")  
	    public String getUser(int mid,Model model){
	        model.addAttribute("user", managerService.findUserById(mid));  
	        return "editUser";
	    }
	
	    
	    @RequestMapping("delManager") 
	    public String deleteUser(HttpServletRequest req) {
	     Integer mid=Integer.parseInt(req.getParameter("id"));
	     managerService.deleteManager(mid);
	        return "redirect:ManagerInfo";
	    }

	    
	     ///分页查询管理员信息
	 
	    @RequestMapping("ManagerInfo")
	    public String getUsers(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
	        //从第一条开始 每页查询五条数据
	        PageHelper.startPage(pn, 5);
	        List<Manager> manager = managerService.findAll();
	        //将用户信息放入PageInfo对象里
	        PageInfo page = new PageInfo( manager,5);
	        model.addAttribute("pageInfo", page);
	        return "houtai/allManager.jsp";
	       /* return "houtai/test.jsp";*/
	    }
	    
	    //返回后台首页
	    @RequestMapping("index")
	    public String index(){
	        return "houtai/index.jsp";
	    }
	    
	    //二级密码验证
	    @RequestMapping("check")
	    public String check(HttpServletRequest req){
	    	String password=req.getParameter("managerPassword");
	    	if("erjimima".equals(password)){
	    		 return "manager/ManagerInfo";
	    	}
	    	
	    	else {
	    		return "houtai/index.jsp";
	    				
	    	}
	    }
}
