package com.arketeam.addenplus.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arketeam.addenplus.models.Doc;
import com.arketeam.addenplus.repository.ArticleRepository;
import com.arketeam.addenplus.repository.DocRepository;
import com.arketeam.addenplus.security.jwt.JwtUtils;
import com.arketeam.addenplus.services.ArticleService;
import com.arketeam.addenplus.services.DocService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocController.class)
public class DocControllerTests {
	
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DocService docService;
    @MockBean
    private DocRepository docRepository;
    @MockBean
    private ArticleService articleService;
    @MockBean
    private ArticleRepository articleRepository;
    @MockBean
    JwtUtils jwtUtils;
    @InjectMocks
    private DocController docController;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(docController).build();
    }
    @Test
    public void testGetImageByArtCodeBarre() throws Exception {
        Doc doc = new Doc("ART1", "Sample Image", "C:/DEV/JAVA_17_stage/API_doc_images/Chaise1.jpg", (short) 1);
        given(docService.getDefaultDocsByArtCodeBarre(anyString())).willReturn(Optional.of(doc));
        mockMvc.perform(get("/api/docs/pool1/images")
                        .param("artCodeBarre", "333444555666")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(header().string("Content-Disposition", "form-data; name=\"filename\"; filename=\"Sample Image\""));
    }
    @Test
    public void testImageNotFound() throws Exception {
        given(docService.getDefaultDocsByArtCodeBarre(anyString())).willReturn(Optional.empty());

        mockMvc.perform(get("/api/docs/pool1/images")
                        .param("artCodeBarre", "nonexistent")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isNotFound());
    }
}
