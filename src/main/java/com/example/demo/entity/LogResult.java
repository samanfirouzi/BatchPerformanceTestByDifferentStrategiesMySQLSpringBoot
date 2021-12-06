package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "log_result")
public class LogResult {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "strategy", nullable = false)
  private String strategy;
  @Column(name = "time_mili", nullable = false)
  private Long timeMili;
  @Column(name = "number_of_row", nullable = false)
  private Integer numberOfRow;
  @Column(name = "number_of_thread", nullable = false)
  private Integer numberOfThread;
  @Column(name = "count_of_exist_row", nullable = false)
  private Long countOfExistRow;
  @Column(name = "new_count_row", nullable = false)
  private Long newCountRow;
  @Column(name = "round_number", nullable = false)
  private int roundNumber;
  @Column(name = "test_case", nullable = false)
  private String testCase;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStrategy() {
    return strategy;
  }

  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }

  public Long getTimeMili() {
    return timeMili;
  }

  public void setTimeMili(Long timeMili) {
    this.timeMili = timeMili;
  }

  public Integer getNumberOfRow() {
    return numberOfRow;
  }

  public void setNumberOfRow(Integer numberOfRow) {
    this.numberOfRow = numberOfRow;
  }

  public Integer getNumberOfThread() {
    return numberOfThread;
  }

  public void setNumberOfThread(Integer numberOfThread) {
    this.numberOfThread = numberOfThread;
  }

  public String getTestCase() {
    return testCase;
  }

  public void setTestCase(String testCase) {
    this.testCase = testCase;
  }

  public Long getCountOfExistRow() {
    return countOfExistRow;
  }

  public void setCountOfExistRow(Long countOfExistRow) {
    this.countOfExistRow = countOfExistRow;
  }

  public Long getNewCountRow() {
    return newCountRow;
  }

  public void setNewCountRow(Long newCountRow) {
    this.newCountRow = newCountRow;
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(int roundNumber) {
    this.roundNumber = roundNumber;
  }
}
