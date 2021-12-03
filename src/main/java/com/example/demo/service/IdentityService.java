package com.example.demo.service;

import com.example.demo.entity.IdentitySample;
import com.example.demo.repo.IdentityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityService extends BaseService<IdentitySample, Long> {
  @Autowired
  public IdentityService(IdentityRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected IdentitySample newEntity() {
    return new IdentitySample();
  }
}
