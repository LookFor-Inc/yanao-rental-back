package com.lookfor.yanaorental.repositories.auth;

import com.lookfor.yanaorental.models.auth.AuthProvider;
import com.lookfor.yanaorental.models.auth.ProviderAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProviderAuthRepository extends CrudRepository<ProviderAuth, String> {
    Optional<ProviderAuth> findByIdAndProvider(String id, AuthProvider authProvider);
}
