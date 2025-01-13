package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arketeam.addenplus.models.Article;
import com.arketeam.addenplus.models.Doc;
import com.arketeam.addenplus.models.Geo;
import com.arketeam.addenplus.repository.ArticleRepository;

import jakarta.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleRepository articleRepository;
	@Override
	@Transactional
	public void save(Article article) {
		 articleRepository.save(article);
	}
	@Override
	public List<Article> listAll() {
	    return (List<Article>)articleRepository.findAll();
	}
	@Override
	public Optional<List<Article>> getByGeoCodeBarre(String geoCodeBarre) {
		return articleRepository.findAllByArtGeo_GeoCodeBarre(geoCodeBarre);
	}
	@Override
	public Optional<Article> getByArtCodeBarre(String artCodeBarre) {
		return articleRepository.findByArtCodeBarre(artCodeBarre);
	}
}
