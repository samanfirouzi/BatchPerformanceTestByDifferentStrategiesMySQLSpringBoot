package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sequence_sample")
public class SequenceSample extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
  @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1, allocationSize = 200)
  private Long id;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
