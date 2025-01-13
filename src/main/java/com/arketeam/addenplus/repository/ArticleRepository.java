package com.arketeam.addenplus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arketeam.addenplus.models.Article;

public interface ArticleRepository extends	CrudRepository<Article, String>{

	Optional<Article> findByArtCodeBarre(String artCodeBarre);
	Optional<List<Article>> findAllByArtGeo_GeoCodeBarre(String geoCodeBarre);
	
}
//Optional<Article> findByGeoCodeBarre(String geoCodeBarre);