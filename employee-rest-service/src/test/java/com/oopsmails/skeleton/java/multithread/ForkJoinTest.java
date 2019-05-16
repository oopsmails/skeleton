package com.oopsmails.skeleton.java.multithread;

import com.oopsmails.skeleton.TestUtils;
import com.oopsmails.skeleton.springboot.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ForkJoinTest {
    private int batchSize = 50;
    private int parallelRestClientCalls = 4;

    List<Employee> mockedEmployeeList = new ArrayList<>();

    @Before
    public void setup() {
        mockedEmployeeList = TestUtils.getMockedEmployeeList();
    }

    @Test
    public void tempTargetDate1() throws Exception {
//        Set<Integer> idSet = new HashSet<>();
//        IntStream.range(1, 5).forEach((int i) -> {
//            idSet.add(i);
//        });
//
////        List<Employee> requests = splitToBatches(securitiesToRetrieveFromMOD, fromDate, toDate, limit, insightTypes);
//        Callable<Employee> optionalCallable = () ->
//                mockedEmployeeList.stream().parallel()
//                        .map(employee -> callRestClientToUpdate(employee))
//                        .reduce((result, element) -> mergeModReports(result, element))
//                        .get();
//
//        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelRestClientCalls);
//        return forkJoinPool.submit(optionalCallable).join();
    }


}

//    @SetBind(BindingConstants.SECURITIES_FROM_MOD)
//    public SecurityEventsReport process(@Bind(value = BindingConstants.FROM_DATE, required = false) Date fromDate,
//                                        @Bind(value = BindingConstants.TO_DATE, required = false) Date toDate,
//                                        @Bind(BindingConstants.LIMIT) Integer limit, @Bind(BindingConstants.SECURITIES_TO_RETRIEVE) Set<SimpleSecurity> securitiesToRetrieveFromMOD,
//                                        @Bind(BindingConstants.INSIGHT_TYPES) Set<SecurityEventType> insightTypes) {
//
//        List<SecurityEventsDataRequest> requests = splitToBatches(securitiesToRetrieveFromMOD, fromDate, toDate, limit, insightTypes);
//        Callable<SecurityEventsReport> optionalCallable = () ->
//                requests.stream().parallel()
//                        .map(reportRequest -> callModForSecurityEventsReport(reportRequest))
//                        .reduce((result, element) -> mergeModReports(result, element))
//                        .get();
//
//        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelRestClientCalls);
//        return forkJoinPool.submit(optionalCallable).join();
//    }

//    private List<SecurityEventsDataRequest> splitToBatches(Set<SimpleSecurity> securities,
//                                                           Date fromDate, Date toDate, Integer limit, Set<SecurityEventType> insightTypes) {
//
//        SimpleSecurity[] simpleSecurities = securities.toArray(new SimpleSecurity[securities.size()]);
//        List<SecurityEventsDataRequest> result = new ArrayList<>();
//        for (int i = 0; i <= simpleSecurities.length; i += batchSize) {
//            HashSet<SimpleSecurity> securitiesBatch = new HashSet<>(Arrays.asList(Arrays.copyOfRange(simpleSecurities, i,
//                    (i + batchSize) > simpleSecurities.length ? simpleSecurities.length : i + batchSize)));
//
//            SecurityEventsDataRequest request = new SecurityEventsDataRequest(i, securitiesBatch, fromDate,
//                    toDate, limit, insightTypes);
//            result.add(request);
//        }
//        return result;
//    }

//    private SecurityEventsReport mergeModReports(SecurityEventsReport result, SecurityEventsReport element) {
//        logger.trace("Merging SecurityEventsReport id [{}] to id [{}]", element.getId(), result.getId());
//        return result.merge(element);
//    }

//    public SecurityEventsReport callModForSecurityEventsReport(SecurityEventsDataRequest request) {
//        logger.trace("Retrieving SecurityEventsReport for SecurityEventsDataRequest batchId [{}]", request.getBatchId());
//
//        SecurityEventsDataPackage dataPackage = modRestClient.getEvents(
//                request.getSecurities(), request.getFromDate(), request.getToDate(),
//                request.getLimit(), request.getEventTypes());
//
//        SecurityEventsReport result = dataPackage.getData();
//        result.setFromDate(request.getFromDate());
//        result.setToDate(request.getToDate());
//
//        logger.trace("Retrieved SecurityEventsReport id [{}] for SecurityEventsDataRequest batchId [{}]",
//                result.getId(), request.getBatchId());
//
//        return result;
//    }

//    public SecurityEventsReport merge(SecurityEventsReport report) {
//        this.addSecurities(report.getSecurities());
//        this.addNews(report.getNews());
//        this.addDividends(report.getDividends());
//        this.addEarnings(report.getEarnings());
//        this.addRatingsChange(report.getRatingsChange());
//        return this;
//    }

