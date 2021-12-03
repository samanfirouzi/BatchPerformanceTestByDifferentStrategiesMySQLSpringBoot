package com.example.demo.service;

import com.example.demo.entity.UUIDStringSample;
import com.example.demo.repo.UUIDStringRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UUIDStringService extends BaseService<UUIDStringSample, String> {
  @Autowired
  public UUIDStringService(UUIDStringRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected UUIDStringSample newEntity() {
    return new UUIDStringSample();
  }
}
