package com.example.demo;

import com.example.demo.entity.LogResult;
import com.example.demo.service.LogResultService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class LoggerSingleton {
  private static final String SEPRATOR = "==============================================";
  private static LoggerSingleton instance;
  private Map<String, List<Boolean>> threadCount = new ConcurrentHashMap<>();
  private long time;
  private boolean LOGGED = false;

  private LoggerSingleton() {
  }

  public static LoggerSingleton get() {
    if (instance == null) {
      instance = new LoggerSingleton();
    }
    return instance;
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


  public synchronized int add(String strategy) {
    List<Boolean> threadActiveNum = threadCount.get(strategy);
    if (threadActiveNum == null) {
      threadActiveNum = new ArrayList<>();
      threadCount.put(strategy, threadActiveNum);
      LOGGED = false;
      tik();
      log.info(SEPRATOR);
      log.info("{} testcase started", strategy);
    }
    threadActiveNum.add(true);

    return threadActiveNum.size();
  }

  public synchronized void remove(String strategy, String testCase, long existRowCount) {
    var counter = threadCount.get(strategy);
    counter.remove(0);
    log.info("{} thread {} Finished", strategy, counter.size());
    if (counter.isEmpty() && !LOGGED) {
      LOGGED = true;
      long duration = tak(strategy, testCase);
      log.info("{} testcase finished", strategy);
      log.info(SEPRATOR);
      threadCount.remove(strategy);
      saveLogResult(strategy, testCase, duration, existRowCount);
    }
  }

  private void saveLogResult(String strategy, String testCase, long duration, long existRowCount) {
    LogResultService service = ContextLoader.getService(LogResultService.class);
    LogResult logResult = new LogResult();
    logResult.setCountOfExistRow(existRowCount);
    logResult.setNewCountRow(existRowCount + ((long) Constant.ROWS * Constant.THREAD_COUNT));
    logResult.setNumberOfRow(Constant.ROWS);
    logResult.setNumberOfThread(Constant.THREAD_COUNT);
    logResult.setStrategy(strategy);
    logResult.setTestCase(testCase);
    logResult.setTimeMili(duration);
    logResult.setRoundNumber(DemoApplication.ROUND);
    service.saveResult(logResult);
    DemoApplication.isRunning = false;
  }


  public void header() {
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
