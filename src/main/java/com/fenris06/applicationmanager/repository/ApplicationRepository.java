package com.fenris06.applicationmanager.repository;

import com.fenris06.applicationmanager.model.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser_IdOrderByCreateDateAsc(Long id, Pageable pageable);

    List<Application> findByUser_IdOrderByCreateDateDesc(Long id, Pageable pageable);





}