package kz.baltabayev.newsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.baltabayev.newsservice.model.entity.News;
import kz.baltabayev.newsservice.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(buildNews.getId()))
                .andExpect(jsonPath("$[0].title").value(buildNews.getTitle()))
                .andExpect(jsonPath("$[0].content").value(buildNews.getContent()))
                .andExpect(jsonPath("length()").value(newsList.size()));

        verify(newsService, times(1)).getAll();
    }

    @Test
    void create() throws Exception {
        News expectedNews = News.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        when(newsService.save(anyString(), anyString(), anyList(), anyList())).thenReturn(expectedNews);

        MockMultipartFile file = new MockMultipartFile(
                "files",
                "test.txt",
                "text/plain",
                "test data".getBytes()
        );

        MvcResult mvcResult = mockMvc.perform(multipart("/api/v1/news")
                        .file(file)
                        .param("title", "title")
                        .param("content", "content")
                        .param("tags", "tag1", "tag2"))
                .andExpect(status().isOk())
                .andReturn();

        News actualNews = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), News.class);
        assertEquals(expectedNews, actualNews);

        verify(newsService, times(1)).save(anyString(), anyString(), anyList(), anyList());
    }

    @Test
    void update() throws Exception {
        News expectedNews = News.builder()
                .id(1L)
                .title("some title")
                .content("some content")
                .build();

        when(newsService.update(anyLong(), anyString(), anyString(), anyList())).thenReturn(expectedNews);

        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/news/{id}", 1L)
                        .param("title", "some title")
                        .param("content", "some content")
                        .param("tags", "tags1", "tags2"))
                .andExpect(status().isOk())
                .andReturn();

        News updatedNews = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), News.class);
        assertEquals(expectedNews, updatedNews);

        verify(newsService, times(1)).update(anyLong(), anyString(), anyString(), anyList());
    }

    @Test
    void getNews() throws Exception {
        News expectedNews = News.builder()
                .id(1L)
                .title("some title")
                .build();

        when(newsService.getById(expectedNews.getId())).thenReturn(expectedNews);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/news/{id}", 1L))
                .andExpect(jsonPath("$.title").value(expectedNews.getTitle()))
                .andExpect(status().isOk())
                .andReturn();

        News receivedNews = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), News.class);
        assertEquals(expectedNews, receivedNews);

        verify(newsService, times(1)).getById(anyLong());
    }

    @Test
    void deleteNews() throws Exception {
        News expectedNews = News.builder()
                .id(1L)
                .title("some title")
                .build();

        when(newsService.getById(expectedNews.getId())).thenReturn(expectedNews);
        mockMvc.perform(delete("/api/v1/news/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
