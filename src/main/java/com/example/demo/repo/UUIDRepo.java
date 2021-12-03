package com.example.demo.repo;

import com.example.demo.entity.UUIDSample;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UUIDRepo extends BaseRepo<UUIDSample, UUID> {
}
