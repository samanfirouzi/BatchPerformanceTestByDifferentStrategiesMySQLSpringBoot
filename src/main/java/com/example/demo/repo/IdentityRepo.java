package com.example.demo.repo;

import com.example.demo.entity.IdentitySample;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepo extends BaseRepo<IdentitySample, Long> {
}
