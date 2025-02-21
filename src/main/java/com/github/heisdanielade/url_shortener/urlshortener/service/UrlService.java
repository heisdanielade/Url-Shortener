package com.github.heisdanielade.url_shortener.urlshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import com.github.heisdanielade.url_shortener.urlshortener.model.Url;
import com.github.heisdanielade.url_shortener.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        if (originalUrl == null || originalUrl.isEmpty()) {
            throw new IllegalArgumentException("(e) Original URL cannot be null or empty.");
        }
        Url existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl != null) {
            return existingUrl.getShortUrl();
        }

        String shortUrl;
        do {
            shortUrl = RandomStringUtils.randomAlphanumeric(8);
        } while (urlRepository.findByShortUrl(shortUrl) != null);

        // save url details to DB
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);

        return shortUrl;
    }


    public String getOriginalUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url != null ? url.getOriginalUrl() : null;
    }
}
