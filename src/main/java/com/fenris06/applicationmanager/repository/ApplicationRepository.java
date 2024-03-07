package com.fenris06.applicationmanager.repository;

import com.fenris06.applicationmanager.model.Application;
import com.fenris06.applicationmanager.model.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByIdInAndStatus(Collection<Long> ids, Status status);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.id = :id " +
            "ORDER BY a.createDate ASC")
    List<Application> findApplicationsByUserAsc(Long id, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.id = :id " +
            "ORDER BY a.createDate DESC")
    List<Application> findApplicationsByUserDESC(Long id, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.username LIKE %:userName% AND a.status IN (:statuses)" +
            "ORDER BY a.createDate ASC")
    List<Application> findApplicationsByUserNameAndStatusAsc(String userName, Collection<Status> statuses, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.username LIKE %:userName% AND a.status IN (:statuses)" +
            "ORDER BY a.createDate DESC")
    List<Application> findApplicationsByUserNameAndStatusDesc(String userName, Collection<Status> statuses, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.status IN (:statuses) " +
            "ORDER BY a.createDate ASC")
    List<Application> findApplicationsByStatusAsc(Collection<Status> statuses, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.status IN (:statuses) " +
            "ORDER BY a.createDate DESC")
    List<Application> findApplicationsByStatusDesc(Collection<Status> statuses, Pageable pageable);
}