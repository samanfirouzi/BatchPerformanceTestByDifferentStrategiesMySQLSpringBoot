package com.example.demo.service;

import com.example.demo.entity.AutoNativeSample;
import com.example.demo.repo.AutoNativeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoNativeService extends BaseService<AutoNativeSample, Long> {
  @Autowired
  public AutoNativeService(AutoNativeRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected AutoNativeSample newEntity() {
    return new AutoNativeSample();
  }
}
