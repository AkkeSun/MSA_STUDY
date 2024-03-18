package com.example.oauth2.adopter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Transactional(readOnly = true)
    boolean existsByUserId(String username);

    @Transactional(readOnly = true)
    Optional<AccountEntity> findByUserId(String username);

    @Transactional(readOnly = true)
    Optional<AccountEntity> findByUserIdAndSnsSync(String username, String snsSync);
}
