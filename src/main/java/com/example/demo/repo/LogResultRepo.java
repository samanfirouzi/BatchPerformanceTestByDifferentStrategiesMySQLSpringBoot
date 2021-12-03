package com.example.demo.repo;

import com.example.demo.entity.LogResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogResultRepo extends JpaRepository<LogResult, Long> {
}
