package com.example.demo.repo;

import com.example.demo.entity.SequenceSample;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepo extends BaseRepo<SequenceSample, Long> {
}
