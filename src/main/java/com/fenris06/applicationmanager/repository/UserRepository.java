package com.fenris06.applicationmanager.repository;

import com.fenris06.applicationmanager.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u " +
            "from User u " +
            "join fetch u.roles")
    List<User> findAllWithRoles(Pageable pageable);

    @Query(value = "SELECT u.*" +
            "FROM users AS u " +
            "JOIN user_roles AS ur ON u.id = ur.user_id " +
            "JOIN roles AS r ON ur.role_id = r.id " +
            "WHERE u.id in (?) AND r.name <> ROLE_OPERATOR", nativeQuery = true)
    List<User> findAllForUpdateRole(Set<Long> ids, String role);

    List<User> findByIdInAndRoles_NameNot(Collection<Long> ids, String name);


}