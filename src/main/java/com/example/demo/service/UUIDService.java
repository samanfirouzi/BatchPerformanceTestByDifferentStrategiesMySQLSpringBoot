package com.example.demo.service;

import com.example.demo.entity.UUIDSample;
import com.example.demo.repo.UUIDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService extends BaseService<UUIDSample, UUID> {
  @Autowired
  public UUIDService(UUIDRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected UUIDSample newEntity() {
    return new UUIDSample();
  }
}
