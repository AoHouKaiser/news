package com.news.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.dao.NewsMapper;
import com.news.entity.News;

@Service("getnews")
public class getNews {

	@Resource
	NewsMapper newsMapper;

	public List<News> findnewByhotType(int hot, int type) {
		List<News> list = newsMapper.findNewsByRedu(hot, type);
		return list;
	}

	public List<News> findnewByhot(int hot) {
		List<News> list = newsMapper.findNewsByhot(hot);
		return list;
	}

	public List<News> findnewBytype(int type) {
		List<News> list = newsMapper.findNewsByntid(type);
		return list;
	}

	public News findnewsByID(int nid) {
		News n = newsMapper.selectByPrimaryKey(nid);
		return n;
	}

	public List<News> selectNews() {
		List<News> list = newsMapper.selectByExample(null);
		return list;
	}

	public List<News> findnewntid(int niid) {
		List<News> list = newsMapper.findNewsByntid(niid);
		return list;
	}

	// 按新闻类别查询返回分页列表
	public PageInfo list(int pageNum, List<News> list) {
		PageHelper.startPage(pageNum, 5);
		PageInfo page = new PageInfo(list);
		return page;
	}

	// 按新闻关键字查询返回分页列表
	public PageInfo listBysearchTitle(int pageNum, List<News> list) {
		PageHelper.startPage(pageNum, 5);
		PageInfo page = new PageInfo(list);
		return page;
	}

	// 按新闻关键字查询返回列表
	public List<News> listBysearchTitle_noPage(String title) {

		return newsMapper.listBysearchTitle(title);
	}

	public int gainews(News n) {
		int i = newsMapper.updateByPrimaryKey(n);
		return i;
	}

	public int insert(News n) {
		int i = newsMapper.insert(n);
		return i;
	}

	public int delnews(int nid) {
		int i = newsMapper.deleteByPrimaryKey(nid);
		return i;
	}
	
	 public List<News> suijihuoqv(){
		 List<News> list=newsMapper.selectByExample(null);
		 int[] a = new int[4];
		 a[0]=0;
		 for(int i=1;i<=3;i++){
		 a[i]=(int) (Math.random()*list.size());
		 if(a[i]==a[i-1]){i--;}
		}
		 List<News> list2=newsMapper.selectByExample(null);
		 list2.clear();
		 for(int j=1;j<4;j++)
		 for(int i=0;i<list.size();i++){
			 if(a[j]==i){
				 list2.add(list.get(i));
			 }
		 }
		return list2;
	 }
	 
 public List<News> findByTypeandTitle(int ntid,String title){
	 return newsMapper.findByTypeandTitle(ntid, title);   
	 
	    }
 
 public List<News> findByTypeandAuthor(int ntid,String author){
	 return newsMapper.findByTypeandAuthor(ntid, author);
 }
	

}
