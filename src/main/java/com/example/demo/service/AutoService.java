package com.example.demo.service;

import com.example.demo.entity.AutoSample;
import com.example.demo.repo.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService extends BaseService<AutoSample, Long> {
  @Autowired
  public AutoService(AutoRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected AutoSample newEntity() {
    return new AutoSample();
  }
}
