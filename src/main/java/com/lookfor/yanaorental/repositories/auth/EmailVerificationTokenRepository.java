package com.lookfor.yanaorental.repositories.auth;

import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {
    Optional<EmailVerificationToken> findByToken(String token);

    boolean existsByToken(String token);
}
