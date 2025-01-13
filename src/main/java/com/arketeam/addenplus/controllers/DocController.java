package com.arketeam.addenplus.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arketeam.addenplus.models.Doc;
import com.arketeam.addenplus.payload.request.NewDocRequest;
import com.arketeam.addenplus.payload.response.MessageResponse;
import com.arketeam.addenplus.repository.ArticleRepository;
import com.arketeam.addenplus.repository.DocRepository;
import com.arketeam.addenplus.security.jwt.JwtUtils;
import com.arketeam.addenplus.services.ArticleService;
import com.arketeam.addenplus.services.DocService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/docs")
public class DocController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	DocRepository docRepository;
	
	@Autowired
	DocService docService;
	
	@Autowired
    JwtUtils jwtUtils;

    @PostMapping("/{pool}/create/doc")
	public ResponseEntity<?> addArticleDoc(@RequestHeader("Authorization") String token,
			@Valid @RequestBody NewDocRequest newDocRequest) {
	    if (token == null || !token.startsWith("Bearer ")) {
	       return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	              .body(new MessageResponse("Unauthorized access"));
	    }
	        
	    Doc doc = new Doc(newDocRequest.getDocParent(),
	    		 newDocRequest.getDocTitle(), 
	    		 newDocRequest.getDocPath(),
	    		 newDocRequest.getDocDefault());
	  
	    docRepository.save(doc);

	    return ResponseEntity.ok(new MessageResponse("GeoArticle added successfully!"));
	}
	
    @GetMapping("/{pool}/images")
	public ResponseEntity<byte[]> getImageByArtCodeBarre(@RequestHeader("Authorization") String token, 
			@RequestParam("artCodeBarre") String artCodeBarre) throws 
	IOException {
		
		  Optional<Doc> documentOptional = docService.getDefaultDocsByArtCodeBarre(artCodeBarre);
	        if (documentOptional.isPresent()) {
	            Doc document = documentOptional.get();
	            String filePath = document.getDocPath();

	            Path path = Paths.get(filePath);
	            byte[] fileContent = Files.readAllBytes(path);

	            String contentType = Files.probeContentType(path);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.parseMediaType(contentType));
	            headers.setContentDispositionFormData("filename", document.getDocTitle());
	            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	}



