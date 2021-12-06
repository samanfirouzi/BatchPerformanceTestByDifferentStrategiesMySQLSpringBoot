package com.example.demo;

import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class DemoApplication {
  public static Boolean isRunning = false;
  public static int ROUND;
  public boolean SAVE_Test;
  public boolean SAVE_ALL_Test;
  public boolean UPDATE_Test;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    LoggerSingleton.get().header();

    SAVE_Test = false;
    SAVE_ALL_Test = false;
    UPDATE_Test = true;
    int loopOnStrategy = Constant.LOOP;
    new Thread(() -> {
      try {
        var choseTest = 1;
        ROUND = 0;
        while (true) {
          if (isRunning) {
            Thread.sleep(1000L);
          } else {
            isRunning = true;
            if (ROUND++ >= loopOnStrategy) {
              choseTest++;
              ROUND = 1;
            }
            if (choseTest >= 6)
              break;
            extracted(choseTest);
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  private void extracted(int choseTest) {
    IntStream.range(0, Constant.THREAD_COUNT).forEach(index -> new Thread(() -> {
      switch (choseTest) {
        case 1:
          runTest(SequenceService.class);
          break;
        case 2:
          runTest(IdentityService.class);
          break;
        case 3:
          runTest(UUIDStringService.class);
          break;
        case 4:
          runTest(AutoNativeService.class);
          break;
        case 5:
          runTest(UUIDService.class);
          break;
        //        case 6:
        //          runTest(AutoService.class);
        //          break;
        default:
          log.info("No test case selected!!!!");
      }
    }).start());
  }

  void runTest(Class<? extends BaseService> service) {
    var strategy = service.getSimpleName().replaceAll("Service", " strategy");
    var baseService = ContextLoader.getService(service);
    long existRowCount = (baseService.count());

    int add = LoggerSingleton.get().add(strategy);

    String testCase = "";

    if (SAVE_Test) {
      baseService.batchTestSave();
      testCase = "SAVE";
    }
    if (SAVE_ALL_Test) {
      baseService.batchTestSaveAll();
      testCase = "SAVE ALL";
    }
    if (UPDATE_Test) {
      if (service.equals(UUIDService.class)) {
        log.error("UUID can not update");
      } else {
        baseService.batchTestUpdate(add);
        testCase = "UPDATE";
      }
    }

    LoggerSingleton.get().remove(strategy, testCase, existRowCount);
  }

}
