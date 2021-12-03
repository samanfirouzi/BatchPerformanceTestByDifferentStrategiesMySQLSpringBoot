package com.example.demo.service;

import com.example.demo.Constant;
import com.example.demo.entity.BaseEntity;
import com.example.demo.repo.BaseRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<E extends BaseEntity, I> {
  BaseRepo<E, I> sampleRepo;

  public BaseService(BaseRepo<E, I> sampleRepo) {
    this.sampleRepo = sampleRepo;
  }

  @Transactional
  public List<E> batchTestSave() {
    List<E> saved = new ArrayList<>();
    for (int i = 0; i < Constant.ROWS; i++) {
      E sampleData = newEntity();
      sampleData.setFakeName("name " + i);
      E save = sampleRepo.save(sampleData);
      saved.add(save);
    }
    return saved;
  }

  protected abstract E newEntity();

  @Transactional
  public void batchTestSaveAll() {
    List<E> sampleDatas = new ArrayList<>();
    for (int i = 0; i < Constant.ROWS; i++) {
      E sampleData = newEntity();
      sampleData.setFakeName("name " + i);
      sampleDatas.add(sampleData);
    }
    sampleRepo.saveAll(sampleDatas);
  }

  @Transactional
  public void batchTestUpdate(List<E> sampleDataList) {
    for (E sampleData : sampleDataList) {
      sampleRepo.save(sampleData);
      //      Optional<E> sampleData1 = sampleRepo.findById(sampleData.getId());
      //      if (sampleData1.isPresent()) {
      //        sampleData1.get().setNameFake(sampleData.getNameFake() + " updated");
      //        sampleRepo.save(sampleData1.get());
      //      }
    }
  }

  public Long count() {
    return sampleRepo.count();
  }
}
