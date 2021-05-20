package com.lookfor.yanaorental.repositories.auth;

import com.lookfor.yanaorental.models.auth.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    boolean existsByToken(String token);
}
