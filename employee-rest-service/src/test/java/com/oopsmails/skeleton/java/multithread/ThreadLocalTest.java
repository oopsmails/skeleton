package com.oopsmails.skeleton.java.multithread;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalStayIfThreadsReused() throws InterruptedException, ExecutionException {
        AtomicInteger threadLocalSetCount = new AtomicInteger();

        ExecutorService exec = Executors.newFixedThreadPool(3);
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String value;
                try {
                    value = SomeContextHolder.get();
                    if (value == null) {
                        int currentCount = threadLocalSetCount.getAndIncrement();
                        String threadDesc = "Count=" + currentCount;
                        SomeContextHolder.set(threadDesc);
                        testThreadLocalValueIsSet(currentCount);
                    } else {
                        Assert.fail("ThreadLocal is not NULL and its value is [" + value + "]");
                    }
                } finally {
                    //if this is not in place we can see how ThreadLocals are reused
                    SomeContextHolder.clean();
                }
                return value;
            }

            private void testThreadLocalValueIsSet(int currentCount) {
                String testValue = SomeContextHolder.get();
                Assert.assertNotNull(testValue);
                Assert.assertEquals("Count=" + currentCount, testValue);
            }
        };

        List<Future<String>> futureResults = new ArrayList<>();
        IntStream.range(0, 100).forEach(a -> futureResults.add(exec.submit(task)));

        for (Future<String> res : futureResults) {
            res.get();
        }
        Assert.assertEquals(100, threadLocalSetCount.get());
    }

    private static class SomeContextHolder {
        private static ThreadLocal<String> testThreadLocal = new ThreadLocal<>();

        public static String get() {
            return testThreadLocal.get();
        }

        public static void set(String value) {
            testThreadLocal.set(value);
        }

        public static void clean() {
            testThreadLocal.remove();
        }
    }

}
