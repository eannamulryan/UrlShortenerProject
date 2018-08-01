package com.em.developer.controller;

import com.em.developer.Application;
import com.em.developer.domain.Url;
import com.em.developer.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@WebMvcTest(UrlController.class)
public class UrlControllerTests {

    @MockBean
    private UrlService urlService;

    @Autowired
    private MockMvc mvc;


    @Test
    public void retrieveEmptyUrlsList() throws Exception {

        // when
        MockHttpServletResponse response = mvc.perform(
        MockMvcRequestBuilders.get("/urls").accept(MediaType.APPLICATION_JSON).content("hello")).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    public void saveUrlTest() throws Exception {

        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl("www.google.ie");
        url.setShortUrl("emurl/b");

        Mockito.when(urlService.saveUrl("www.google.ie"))
                .thenReturn(url);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/url").accept(MediaType.APPLICATION_JSON).content("www.google.ie")).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":1,\"originalUrl\":\"www.google.ie\",\"shortUrl\":\"emurl/b\"}");
    }

    @Test
    public void getOriginalTest() throws Exception {

        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl("www.google.ie");
        url.setShortUrl("emurl/b");

        Mockito.when(urlService.getOriginalUrl("emurl/b"))
                .thenReturn(url);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/originalUrl").accept(MediaType.APPLICATION_JSON).content("emurl/b")).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":1,\"originalUrl\":\"www.google.ie\",\"shortUrl\":\"emurl/b\"}");
    }
}