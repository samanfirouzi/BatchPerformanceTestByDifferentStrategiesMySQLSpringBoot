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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class DemoApplication {
  private static final String SEPRATOR = "==============================================";
  public boolean SAVE_Test;
  public boolean SAVE_ALL_Test;
  public boolean UPDATE_Test;
  public boolean LOGGED = false;

  private long time;
  private Map<Class, Integer> threadCount = new ConcurrentHashMap<>();
  private boolean isRunning = false;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    logAlaki();

    SAVE_Test = false;
    SAVE_ALL_Test = true;
    UPDATE_Test = false;
    int loopOnStrategy = Constant.LOOP;

    new Thread(() -> {
      try {
        int i = 0;
        var choseTest = 1;
        while (true) {
          if(isRunning) {
            Thread.sleep(1000l);
          }else {
            isRunning = true;
            LOGGED = false;

            if(i++>=loopOnStrategy)
            {
              choseTest++;
              i=1;
            }

            if(choseTest>=Constant.LOOP) break;

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
          runTest(UUIDService.class);
          break;
        case 5:
          runTest(AutoService.class);
          break;
        case 6:
          runTest(AutoNativeService.class);
          break;
        default:
          log.info("No test case selected!!!!");
      }
    }).start());
  }

  void runTest(Class<? extends BaseService> service) {
    var strategy = service.getSimpleName().replaceAll("Service", " strategy");
    Integer threadActiveNum = threadCount.get(service);
    if (threadActiveNum == null) {
      threadActiveNum = 0;
    }
    threadCount.put(service, ++threadActiveNum);
    var baseService = ContextLoader.getService(service);
    long existRowCount = (baseService.count());

    if (threadActiveNum == 1) {
      tik();
      log.info(SEPRATOR);
      log.info("{} testcase started", strategy);
    }
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
    threadActiveNum = threadCount.get(service);
    threadCount.put(service, --threadActiveNum);
    if (threadActiveNum < 1 && !LOGGED) {
      LOGGED = true;
      long duration = tak(strategy, testCase);
      log.info("{} testcase finished", strategy);
      log.info(SEPRATOR);

      saveLogResult(strategy, testCase, duration, existRowCount);
    }
  }

  private void saveLogResult(String strategy, String testCase, long duration, long existRowCount) {
    LogResultService service = ContextLoader.getService(LogResultService.class);
    LogResult logResult = new LogResult();
    logResult.setNumberOfRow(Constant.ROWS);
    logResult.setNumberOfThread(Constant.THREAD_COUNT);
    logResult.setStrategy(strategy);
    logResult.setTestCase(testCase);
    logResult.setTimeMili(duration);
    logResult.setCountOfExistRow(existRowCount);
    logResult.setNewCountRow(existRowCount + ((long) Constant.ROWS * Constant.THREAD_COUNT));
    service.saveResult(logResult);
    isRunning = false;
  }

  private long tak(String where, String testCase) {
    var endTime = System.currentTimeMillis();
    long durtion = (endTime - this.time);
    log.info("=====> {} in {} happens in: {} milisecond", testCase, where, durtion);
    this.time = endTime;
    return durtion;
  }

  private void tik() {
    this.time = System.currentTimeMillis();
  }

  private void logAlaki() {
    log.info("");
    log.info("");
    log.info("");
    log.info("");
    log.info(SEPRATOR);
    log.info(SEPRATOR);
    log.info("========= L E T ' S   D O   T H I S ==========");
    log.info(SEPRATOR);
    log.info(SEPRATOR);
  }
}
