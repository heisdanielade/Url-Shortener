package com.github.heisdanielade.url_shortener.urlshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import com.github.heisdanielade.url_shortener.urlshortener.model.Url;
import com.github.heisdanielade.url_shortener.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        if (originalUrl == null || originalUrl.isEmpty()) {
            throw new IllegalArgumentException("(e) Original URL cannot be null or empty.");
        }
        String decodedUrl, encodedUrl = "";
        // Decode the URL if it's encoded
        try {
            decodedUrl = URLDecoder.decode(originalUrl, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        // Check if the URL already exists in the database
        Url existingUrl = urlRepository.findByOriginalUrl(decodedUrl);
        if (existingUrl != null) {
            return existingUrl.getShortUrl();
        }
        // Generate a random short code (8 characters long)
        String shortUrl;
        do {
            shortUrl = RandomStringUtils.randomAlphanumeric(8);
        } while (urlRepository.findByShortUrl(shortUrl) != null);

        // Encode the URL before saving it to the database
        try{
            encodedUrl = URLEncoder.encode(decodedUrl, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        // Save the original URL and short code in the database
        Url url = new Url();
        url.setOriginalUrl(encodedUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);

        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) throws UnsupportedEncodingException {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            // Decode the URL before returning it
            String rawUrl = URLDecoder.decode(url.getOriginalUrl(), StandardCharsets.UTF_8);
            StringBuilder newOriginalUrl = new StringBuilder();

            for(char c: rawUrl.toCharArray()){
                if (c != '='){
                    newOriginalUrl.append(c);
                }
            }
            return newOriginalUrl.toString();
        }
        return null;
    }
}
