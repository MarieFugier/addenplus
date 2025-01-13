package com.arketeam.addenplus.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arketeam.addenplus.models.Article;
import com.arketeam.addenplus.models.Doc;
import com.arketeam.addenplus.models.Geo;
import com.arketeam.addenplus.payload.request.NewArticleRequest;
import com.arketeam.addenplus.payload.request.NewDocRequest;
import com.arketeam.addenplus.payload.request.NewGeoRequest;
import com.arketeam.addenplus.payload.response.MessageResponse;
import com.arketeam.addenplus.repository.ArticleRepository;
import com.arketeam.addenplus.repository.DocRepository;
import com.arketeam.addenplus.repository.GeoRepository;
import com.arketeam.addenplus.security.jwt.JwtUtils;
import com.arketeam.addenplus.services.ArticleService;
import com.arketeam.addenplus.services.DocService;
import com.arketeam.addenplus.services.GeoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/articles")
@Transactional
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	GeoRepository geoRepository;
	
	@Autowired
	GeoService geoService;
	
	@Autowired
    JwtUtils jwtUtils;
	
	@PostMapping("/{pool}/create")
	public ResponseEntity<?> addArticle(@RequestHeader("Authorization") String token,@Valid @RequestBody NewArticleRequest newArticleRequest) {
		 
	    if (token == null || !token.startsWith("Bearer ")) {
	       return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	              .body(new MessageResponse("Unauthorized access"));
	    }
	        
	    Article article = new Article(newArticleRequest.getArtGeoCode(), newArticleRequest.getArtCodeBarre());
	    articleRepository.save(article);
	    return ResponseEntity.ok(new MessageResponse("Article added successfully!"));
	}
	
	@GetMapping("/{pool}/all")
	public ResponseEntity<?> getAllArticles(@RequestHeader("Authorization") String token) {
	    List<Article> articles = articleService.listAll();
	    return ResponseEntity.ok(articles);
	}
	
	@GetMapping("/{pool}/{artCodeBarre}")
	public ResponseEntity<?> getArticleByCodeBarre(@RequestHeader("Authorization") String token, @PathVariable String artCodeBarre) {
		Optional<Article> article = articleService.getByArtCodeBarre(artCodeBarre);
		if (article.isPresent()) {
	        return ResponseEntity.ok(article.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/{pool}/create/geo")
	public ResponseEntity<?> addArticleGeo(@RequestHeader("Authorization") String token,@Valid @RequestBody NewGeoRequest newGeoRequest) {
		 
	    if (token == null || !token.startsWith("Bearer ")) {
	       return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	              .body(new MessageResponse("Unauthorized access"));
	    }
	        
	    Geo geo = new Geo(newGeoRequest.getGeoBatCode(),
	    		 newGeoRequest.getGeoCodeBarre());
	  
	    geoRepository.save(geo);

	    return ResponseEntity.ok(new MessageResponse("GeoArticle added successfully!"));
	}
	
	// Appel de la fonction :
	// /{pool}/article=12345678
	// /{pool}/geo=GEO123456789
	@GetMapping("/{pool}/")
	public ResponseEntity<?> getArticleByCodeBarreAndGeoCodeBarre(@RequestHeader("Authorization") String token, 
			@RequestParam(value = "article", required = false) String artCodeBarre, 
			@RequestParam(value = "geo", required = false) String geoCodeBarre) {
		
		if (token == null || !token.startsWith("Bearer ")) {
		       return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
		              .body(new MessageResponse("Unauthorized access"));
		    }
		
		ResponseEntity<Object> responseEntity = ResponseEntity.notFound().build();
	
		if (geoCodeBarre != null) {
	        Optional<List<Article>> article = articleService.getByGeoCodeBarre(geoCodeBarre);
	        if (article.isPresent()) {
	        	responseEntity =  ResponseEntity.ok(article.get());
	        } else {
	        	responseEntity =  ResponseEntity.notFound().build();
	        }
	    } else if (artCodeBarre != null) {
	        Optional<Article> article = articleService.getByArtCodeBarre(artCodeBarre);
	        if (article.isPresent()) {
	        	responseEntity =  ResponseEntity.ok(article.get());
	        } else {
	        	responseEntity =  ResponseEntity.notFound().build();
	        }
		}
		return responseEntity;
	}
	
}
		
