package com.example.demo.repo;

import com.example.demo.entity.AutoNativeSample;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoNativeRepo extends BaseRepo<AutoNativeSample, Long> {
}
