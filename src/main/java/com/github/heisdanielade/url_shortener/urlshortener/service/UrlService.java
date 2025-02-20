package com.github.heisdanielade.url_shortener.urlshortener.service;

import com.github.heisdanielade.url_shortener.urlshortener.model.Url;
import com.github.heisdanielade.url_shortener.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl){
        String shortUrl = "";
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
