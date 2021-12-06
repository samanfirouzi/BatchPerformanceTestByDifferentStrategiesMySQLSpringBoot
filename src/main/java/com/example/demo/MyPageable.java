package com.example.demo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class MyPageable implements Pageable {
  private final int page;

  public MyPageable(int page) {
    this.page = page;
  }

  @Override public int getPageNumber() {
    return page;
  }

  @Override public int getPageSize() {
    return 1000;
  }

  @Override public long getOffset() {
    return 0;
  }

  @Override public Sort getSort() {
    return Sort.by(Sort.Direction.ASC,"id");
  }

  @Override public Pageable next() {
    return null;
  }

  @Override public Pageable previousOrFirst() {
    return null;
  }

  @Override public Pageable first() {
    return null;
  }

  @Override public Pageable withPage(int pageNumber) {
    return null;
  }

  @Override public boolean hasPrevious() {
    return false;
  }
}
