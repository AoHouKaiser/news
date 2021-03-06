
package com.news.controller;

import com.news.entity.*;
import com.news.entity.vo.regai;
import com.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
public class kaishi {
	@Autowired
    getNewtype getnt;
	@Autowired
    getNews getnews;
	@Autowired
    getReview getr;
	@Autowired
    getSupport getsupport;
	
	@Autowired
    SourceService sourceService;
	
	@RequestMapping("chushihuanewstype")
	public String chushihuanewstype(HttpServletRequest req){
		String url="";
		List<Source> findAllImg = sourceService.findAllImg();
		if(findAllImg!=null)
		req.getSession().setAttribute("slist", findAllImg);
		req.getSession().setAttribute("size1", findAllImg.size());
		if(req.getSession().getAttribute("loginUser")==null){
			req.getSession().setAttribute("userid", 0);}else{
				User u=(User)req.getSession().getAttribute("loginUser");
				req.getSession().setAttribute("userid",u.getUid());
			}
		List<News_type> list=getnt.getnewstype();
		req.getSession().setAttribute("newstypelist", list);
		List<News> list1=getnews.findnewByhot(0);
		List<News> list2=getnews.findnewByhot(0);
		for(int i=0;i<list2.size();i++){
			if(list2.get(i).getNtext().length()>200){
				list2.get(i).setNtext((String) list2.get(i).getNtext().subSequence(0,200));
				}
		}
		req.getSession().setAttribute("newshotlist", list1);
		for(int i=0;i<list1.size()/2;i++){
			News n=new News();
			n=list1.get(i);
			list1.set(i, list1.get(list1.size()-i-1));
			list1.set(list1.size()-i-1, n);
		}
		req.getSession().setAttribute("newshotlistx", list1);
		req.getSession().setAttribute("newshotlist1", list2);
		list1=getnews.findnewByhotType(0, 1);
		req.getSession().setAttribute("newszhenzhihotlist", list1);
		list1=getnews.findnewByhotType(0, 2);
		req.getSession().setAttribute("newsjunshihotlist", list1);
		list1=getnews.findnewByhotType(0, 3);
		req.getSession().setAttribute("newstiyuhotlist", list1);
		list1=getnews.findnewByhotType(0, 4);
		req.getSession().setAttribute("newsyulehotlist", list1);
		List<Support> lists=getsupport.getsup();
		req.getSession().setAttribute("zanzhushang", lists);
		req.getSession().setAttribute("hotsize", list1.size());
		url="redirect:index.jsp";
		return url;
	}
	@RequestMapping("xinwenchushihua")
	public String xinwenchushihuan(HttpServletRequest req, HttpSession se){
		
		String url="";
		if(req.getSession().getAttribute("loginUser")==null){
			req.getSession().setAttribute("userid", 0);}else{
				User u=(User)req.getSession().getAttribute("loginUser");
				req.getSession().setAttribute("userid",u.getUid());
			}
		List<Source> findAllImg = sourceService.findAllImg();
		if(findAllImg!=null)
		req.getSession().setAttribute("slist", findAllImg);
		req.getSession().setAttribute("size1", findAllImg.size());
		List<News_type> list=getnt.getnewstype();
		req.getSession().setAttribute("newstypelist", list);
		String flag=req.getParameter("flag");
		int s=Integer.parseInt(flag);
		se.setAttribute("newsid", s);
		int nid=(Integer) req.getSession().getAttribute("newsid");
		News n=getnews.findnewsByID(nid);
		req.getSession().setAttribute("newsxinxi", n);
		List<News> list3=getnews.suijihuoqv();
		req.getSession().setAttribute("tuijie", list3);
		if(n.getIsreview()==1){
		List<regai> rlist=getr.findreviewBynid(nid);
		req.getSession().setAttribute("reviewlist", rlist);
		}
		if(n!=null){
			//url="redirect:xinwenxiangqing.jsp?newsid=nid";
			url="xinwenxiangqing.jsp";
		}else{
			url="redirect:index.jsp";
		}
		return url;
		}
	@RequestMapping("tijisopinglun")
	public String tijiaopinglun(HttpServletRequest req, HttpSession se){
		String url="";
		if(req.getSession().getAttribute("loginUser")==null){
			req.getSession().setAttribute("userid", 0);}else{
				User u=(User)req.getSession().getAttribute("loginUser");
				req.getSession().setAttribute("userid",u.getUid());
			}
		String plxx=req.getParameter("plxx");
		Review r = new Review();
		r.setRtext(plxx);
		int nz=Integer.parseInt((String)req.getParameter("newid"));
		r.setNid(nz);
		//r.setNid(Integer.parseInt((String) req.getParameter("newsid")));
		r.setRtime(new java.sql.Date(new java.util.Date().getTime()));
		r.setUid((Integer) req.getSession().getAttribute("userid"));
		int n=getr.savere(r);
		if(n!=0){
			url="redirect:xinwenchushihua?flag="+nz;
		}else{
			req.getSession().setAttribute("worry", "worry");
			url="redirect:xinwenxiangqing.jsp";
		}
		return url;
	}
}
