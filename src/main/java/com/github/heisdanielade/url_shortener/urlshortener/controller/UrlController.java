package com.github.heisdanielade.url_shortener.urlshortener.controller;

import com.github.heisdanielade.url_shortener.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/api/v1")

public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping(path="/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl ){
        String shortUrl = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        return originalUrl != null ? ResponseEntity.ok(originalUrl) : ResponseEntity.notFound().build();
    }
}
