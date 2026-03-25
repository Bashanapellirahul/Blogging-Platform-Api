package com.BloggingPlatformAPI.Blogging_Platform_API.repository;

import com.BloggingPlatformAPI.Blogging_Platform_API.model.PersonalBlogging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalBloggingRepository extends JpaRepository<PersonalBlogging, Long> {
    Optional<PersonalBlogging> findByTitle(String title);
    Optional<PersonalBlogging> findById(Long id);
}
