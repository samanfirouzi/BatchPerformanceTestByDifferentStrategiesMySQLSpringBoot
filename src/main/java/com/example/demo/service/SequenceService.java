package com.example.demo.service;

import com.example.demo.entity.SequenceSample;
import com.example.demo.repo.SequenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService extends BaseService<SequenceSample, Long> {
  @Autowired
  public SequenceService(SequenceRepo sampleRepo) {
    super(sampleRepo);
  }

  @Override protected SequenceSample newEntity() {
    return new SequenceSample();
  }
}
