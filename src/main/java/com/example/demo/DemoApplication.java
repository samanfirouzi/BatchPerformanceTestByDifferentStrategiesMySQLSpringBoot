package com.example.demo;

import com.example.demo.entity.LogResult;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class DemoApplication {
  public boolean SAVE_Test;
  public boolean SAVE_ALL_Test;
  public boolean UPDATE_Test;
  public static Boolean isRunning = false;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    LoggerSingleton.get().header();

    SAVE_Test = false;
    SAVE_ALL_Test = true;
    UPDATE_Test = false;
    int loopOnStrategy = Constant.LOOP;
    new Thread(() -> {
      try {
        int i = 0;
        var choseTest = 1;
        while (true) {
          if (isRunning) {
            Thread.sleep(1000L);
          } else {
            isRunning = true;
            if (i++ >= loopOnStrategy) {
              choseTest++;
              i = 1;
            }
            if (choseTest >= Constant.LOOP)
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
        case 6:
          runTest(AutoService.class);
          break;
        default:
          log.info("No test case selected!!!!");
      }
    }).start());
  }

  void runTest(Class<? extends BaseService> service) {
    var strategy = service.getSimpleName().replaceAll("Service", " strategy");
    var baseService = ContextLoader.getService(service);
    long existRowCount = (baseService.count());

    int threadActiveNum = LoggerSingleton.get().add(strategy);

    List eList = null;
    String testCase = "";

    if (SAVE_Test) {
      eList = baseService.batchTestSave();
      testCase = "SAVE";
    }
    if (SAVE_ALL_Test) {
      baseService.batchTestSaveAll();
      testCase = "SAVE ALL";
    }
    if (UPDATE_Test && eList != null) {
      baseService.batchTestUpdate(eList);
      testCase = "UPDATE";
    }

    log.info("{} thread {} Finished", strategy, threadActiveNum);
    LoggerSingleton.get().remove(strategy, testCase,existRowCount);
  }

}
