package com.example.demo.repo;

import com.example.demo.entity.UUIDStringSample;
import org.springframework.stereotype.Repository;

@Repository
public interface UUIDStringRepo extends BaseRepo<UUIDStringSample, String> {
}
