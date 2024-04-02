package com.fenris06.applicationmanager.repository;

import com.fenris06.applicationmanager.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u " +
            "from User u " +
            "join fetch u.roles")
    List<User> findAllWithRoles(Pageable pageable);

    List<User> findByIdInAndRoles_NameNot(Collection<Long> ids, String name);

    @Query("SELECT u " +
            "FROM User u " +
            "JOIN FETCH u.roles " +
            "WHERE u.username = :username")
    Optional<User> findAuthorizationUser(String username);

    Optional<User> findByUsername(String username);


}