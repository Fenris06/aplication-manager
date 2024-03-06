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

    List<Application> findByUser_IdOrderByCreateDateAsc(Long id, Pageable pageable);//TODO отрефакторить методы

    List<Application> findByUser_IdOrderByCreateDateDesc(Long id, Pageable pageable);

   // List<Application> findByUser_UserNameLikeAndStatusInOrderByCreateDateAsc(String userName, Collection<Status> statuses, Pageable pageable);

    //List<Application> findByUser_UserNameLikeAndStatusInOrderByCreateDateDesc(String userName, Collection<Status> statuses, Pageable pageable);
    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.username LIKE %:userName% AND a.status IN (:statuses)" +
            "ORDER BY a.createDate ASC")
    List<Application> findByUser_UserNameLikeAndStatusInOrderByCreateDateASC(String userName, Collection<Status> statuses, Pageable pageable);
    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.username LIKE %:userName% AND a.status IN (:statuses)" +
            "ORDER BY a.createDate DESC")
    List<Application> findByUser_UserNameLikeAndStatusInOrderByCreateDateDesc(String userName, Collection<Status> statuses, Pageable pageable);

    //List<Application> findByStatusInOrderByCreateDateAsc(Collection<Status> statuses, Pageable pageable);

   // List<Application> findByStatusInOrderByCreateDateDesc(Collection<Status> statuses, Pageable pageable);
   @Query("SELECT a " +
           "FROM Application a " +
           "JOIN FETCH a.user " +
           "WHERE a.status IN (:statuses) " +
           "ORDER BY a.createDate ASC")
   List<Application> findByStatusInOrderByCreateDateAsc(Collection<Status> statuses, Pageable pageable);

    @Query("SELECT a " +
            "FROM Application a " +
            "JOIN FETCH a.user " +
            "WHERE a.status IN (:statuses) " +
            "ORDER BY a.createDate DESC")
    List<Application> findByStatusInOrderByCreateDateDesc(Collection<Status> statuses, Pageable pageable);
}