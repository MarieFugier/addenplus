package com.arketeam.addenplus.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arketeam.addenplus.models.Article;
import com.arketeam.addenplus.repository.ArticleRepository;
import com.arketeam.addenplus.repository.DocRepository;
import com.arketeam.addenplus.repository.GeoRepository;
import com.arketeam.addenplus.security.jwt.JwtUtils;
import com.arketeam.addenplus.services.ArticleService;
import com.arketeam.addenplus.services.GeoService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleService articleService; 
    @MockBean
    private ArticleRepository articleRepository;
    @MockBean
    private DocRepository docRepository;
    @MockBean
	GeoRepository geoRepository;
    @MockBean
	GeoService geoService;
    @MockBean
    JwtUtils jwtUtils;
    @InjectMocks
    private ArticleController articleController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void testGetArticleByCodeBarre() throws Exception {
        Article article = new Article("GEO1", "111222333444");
        given(articleService.getByArtCodeBarre(anyString())).willReturn(Optional.of(article));

        mockMvc.perform(get("/api/articles/pool1/111222333444")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artGeoCode").value("GEO1"))
                .andExpect(jsonPath("$.artCodeBarre").value("111222333444"));
    }

    @Test
    public void testAddArticle() throws Exception {
        String newArticleJson = "{\"artGeoCode\": \"GEO5\", \"artCodeBarre\": \"555666777888\"}";

        mockMvc.perform(post("/api/articles/pool1/create")
                        .header("Authorization", "Bearer validToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newArticleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Article added successfully!"));
    }

    @Test
    public void testGetAllArticles() throws Exception {
        List<Article> articles = List.of(
            new Article("GEO1", "111222333444"),
            new Article("GEO2", "222333444555")
        );
        given(articleService.listAll()).willReturn(articles);

        mockMvc.perform(get("/api/articles/pool1/all")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artGeoCode").value("GEO1"))
                .andExpect(jsonPath("$[0].artCodeBarre").value("111222333444"))
                .andExpect(jsonPath("$[1].artGeoCode").value("GEO2"))
                .andExpect(jsonPath("$[1].artCodeBarre").value("222333444555"));
    }

    @Test
    public void testGetArticleByCodeBarreAndGeoCodeBarre() throws Exception {
        List<Article> articles = List.of(new Article("GEO1", "111222333444"));
        given(articleService.getByGeoCodeBarre("GEO1")).willReturn(Optional.of(articles));

        mockMvc.perform(get("/api/articles/pool1/")
                        .header("Authorization", "Bearer validToken")
                        .param("geo", "GEO1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artGeoCode").value("GEO1"))
                .andExpect(jsonPath("$[0].artCodeBarre").value("111222333444"));
    }

    @Test
    public void testAddArticleGeo() throws Exception {
        String newGeoJson = "{\"geoBatCode\": \"111\", \"geoCodeBarre\": \"555666777888\"}";

        mockMvc.perform(post("/api/articles/pool1/create/geo")
                        .header("Authorization", "Bearer validToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newGeoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("GeoArticle added successfully!"));
    }

    @Test
    public void testArticleNotFound() throws Exception {
        given(articleService.getByArtCodeBarre(anyString())).willReturn(Optional.empty());

        mockMvc.perform(get("/api/articles/pool1/nonexistent")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isNotFound());
    }
}


