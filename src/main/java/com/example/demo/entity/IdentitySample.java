package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "identity_sample")
public class IdentitySample extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
