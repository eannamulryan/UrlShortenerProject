package com.em.developer.domain;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

    @Entity
    @Getter @Setter
    @NoArgsConstructor
    @ToString @EqualsAndHashCode
    public class Url {
        @Id @GeneratedValue
        private Long id;
        private @NonNull String originalUrl;
        private String shortUrl;
    }

