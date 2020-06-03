package com.myhome.www.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.www.article.dto.Article;
import com.myhome.www.article.service.ArticleService;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDao articleDao;
	
//	@Autowired
//	private ArticleMapper ArticleMapper;
	
	@Override
	public List<Article> selectArticleList() throws Exception {
		return articleDao.selectArticleList();
	}

	@Override
	public int insertArticle(Article article) throws Exception {
		article.setArticleNo(article.getArticleNo());
		return articleDao.insertArticle(article);
	}

	@Override
	public Article selectArticleByNo(int articleNo) throws Exception {
		articleDao.increaseReadCount(articleNo);
		return articleDao.selectArticleByNo(articleNo);
	}

	@Override
	public int updateArticle(Article article) throws Exception {
		return articleDao.updateArticle(article);
	}

	@Override
	public int deleteArticle(Article article) throws Exception {
		System.out.println("============================");
		System.out.println("============================");
		System.out.println("============================");
		System.out.println("============================");
		int articleNo = article.getArticleNo();
		return articleDao.deleteArticle(articleNo);
	}
	
	public void increaseReadCount(Article article) throws Exception {
		articleDao.increaseReadCount(article.getReadCount());
	}

	@Override
	public int selectLastArticleNo() throws Exception {
		List<Integer> res = articleDao.selectLastArticleNo();
		for(int i : res) {
			System.out.println("=========>" + i);
		}
		if(res.isEmpty()) {
			System.out.println("암껏두 안나옴");
			return 0;
		}
		return res.get(0);
		
	}
}
