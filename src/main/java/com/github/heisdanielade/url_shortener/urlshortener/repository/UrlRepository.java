package com.github.heisdanielade.url_shortener.urlshortener.repository;

import com.github.heisdanielade.url_shortener.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}
