package com.example.demo.repo;

import com.example.demo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<E extends BaseEntity, I> extends JpaRepository<E, I> {
}
