package com.example.demo.repo;

import com.example.demo.entity.AutoSample;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepo extends BaseRepo<AutoSample, Long> {
}
