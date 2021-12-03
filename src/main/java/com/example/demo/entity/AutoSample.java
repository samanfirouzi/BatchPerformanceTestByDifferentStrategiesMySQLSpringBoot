package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "auto_sample")
public class AutoSample extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
