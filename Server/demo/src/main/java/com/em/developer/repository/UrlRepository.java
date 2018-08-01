package com.em.developer.repository;

import com.em.developer.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.99.100"} )
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query(value = "from Url u where u.id = :id")
    Optional<Url> findById(
            @Param(value = "id") Long id);

    @Query(value = "from Url u where u.originalUrl = :originalUrl")
    Optional<Url> findByOriginalURL(
            @Param(value = "originalUrl") String originalUrl);

    @Query(nativeQuery = true, value = "CALL IDENTITY()")
    Long getIdWithNextUniqueId() ;

}





