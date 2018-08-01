package com.em.developer.service;

import com.em.developer.domain.Url;

import java.util.Collection;

public interface UrlService {
    Collection<Url> getUrls();
    Url saveUrl(String originalURL);
    Url getOriginalUrl(String shortUrl);
}


