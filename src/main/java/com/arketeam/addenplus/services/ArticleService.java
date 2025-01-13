package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import com.arketeam.addenplus.models.Article;
import com.arketeam.addenplus.models.Doc;

public interface ArticleService {
	
	void save(Article article);
	List<Article> listAll();
	Optional<List<Article>> getByGeoCodeBarre(String geoCodeBarre);
	Optional<Article> getByArtCodeBarre(String artCodeBarre);
}
