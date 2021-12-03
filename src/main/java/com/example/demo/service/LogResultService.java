package com.example.demo.service;

import com.example.demo.entity.LogResult;
import com.example.demo.repo.LogResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogResultService {

  @Autowired LogResultRepo repo;

  public void saveResult(LogResult logResult){
    repo.save(logResult);
  }
}
