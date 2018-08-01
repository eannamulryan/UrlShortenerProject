package com.em.developer.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.em.developer.domain.Url;
import com.em.developer.repository.UrlRepository;
import com.em.developer.utils.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.em.developer.utils.Base62;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Collection<Url> getUrls() {
        return urlRepository.findAll().stream().collect(Collectors.toList());
    }

    /**
     * Retrieve Original Url from Short Url
     */
    public Url getOriginalUrl(String shortUrl) {
        Url url = new Url();
        String str = shortUrl.replace("emurl" +"/", "");

        // Resolve a shortened URL to the initial ID.
        long id = Base62.toBase10(str);
        // Now find your database-record with the ID found
        Optional<Url> urlRes = urlRepository.findById(id);


        if(urlRes.isPresent()) {
            url = urlRes.get();
        }
        return url;
    }

    /**
     * Save Original Url along with generated Short Url.
     */
    public Url saveUrl(String originalUrl) {
        Url url = new Url();
        originalUrl = sanitizeURL(originalUrl);
        // Quickly check the originalURL is already entered in our system.
        Optional<Url> existingUrl = urlRepository.findByOriginalURL(originalUrl);

        if(!existingUrl.isPresent()) {
            url.setOriginalUrl(originalUrl);
            url.setId(urlRepository.getIdWithNextUniqueId() + 1L);
            // Generate short url via base62 encode.
            String shortUrl = "emurl" +"/" + Base62.toBase62(url.getId().intValue());
            url.setShortUrl(shortUrl);

            url = urlRepository.save(url);
        }
        return url;
    }

    /**
     * Method to sanitize url
     */
    private String sanitizeURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

}
