package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContextLoader {

  private static ApplicationContext context;

  @Autowired
  public ContextLoader(ApplicationContext context) {
    ContextLoader.context = context;
  }

  @Transactional
  public static <T> T getService(Class<T> serviceClass) {
    return context.getBean(serviceClass);
  }

  @Transactional
  public static Object getService(String serviceClassName) {
    return context.getBean(serviceClassName);
  }

}
