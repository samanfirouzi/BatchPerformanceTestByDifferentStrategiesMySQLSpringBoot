package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

  @Column(name = "fake_name")
  private String fakeName;

  public String getFakeName() {
    return fakeName;
  }

  public void setFakeName(String fakeName) {
    this.fakeName = fakeName;
  }
}
