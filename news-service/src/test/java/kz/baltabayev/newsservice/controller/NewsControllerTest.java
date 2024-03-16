package kz.baltabayev.newsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.baltabayev.newsservice.model.entity.News;
import kz.baltabayev.newsservice.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @MockBean
    private NewsService newsService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        News buildNews = News.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        List<News> newsList = List.of(buildNews);
        when(newsService.getAll()).thenReturn(newsList);
        mockMvc.perform(get("/api/v1/news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(buildNews.getTitle()))
                .andExpect(jsonPath("$[0].title").value(buildNews.getTitle()))
                .andExpect(jsonPath("$[0].title").value(buildNews.getTitle()))
                .andExpect(jsonPath("length()").value(newsList.size()));

        verify(newsService, times(1)).getAll();
    }
}
