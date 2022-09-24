package com.example.crud_java_project.appuser;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

// TODO!
// @Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);
}
