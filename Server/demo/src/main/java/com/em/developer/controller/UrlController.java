package com.em.developer.controller;

import com.em.developer.domain.Url;
import com.em.developer.repository.UrlRepository;
import com.em.developer.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/urls")
    @CrossOrigin(origins = {"http://localhost:4200", "http://192.168.99.100"} )
    public Collection<Url> urls() {
        return  urlService.getUrls();
    }

    @PostMapping(path = "/url")
    @CrossOrigin(origins = {"http://localhost:4200", "http://192.168.99.100"} )
    public Url addUrl(@RequestBody String originalUrl) {

        return urlService.saveUrl(originalUrl);
    }

    @PostMapping(path = "/originalUrl")
    @CrossOrigin(origins = {"http://localhost:4200", "http://192.168.99.100"} )
    public Url getOriginalUrl(@RequestBody String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
}