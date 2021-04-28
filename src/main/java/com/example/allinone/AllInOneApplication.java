package com.example.allinone;

import com.example.allinone.utilis.HttpUtilis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class AllInOneApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AllInOneApplication.class, args);
        HttpUtilis httpUtilis = new HttpUtilis();
        String url = "http://localhost:8080/post-details";
        String url1 = "http://localhost:8080/get";
        String json = "{ \"userId\": 4,\"customerId\": 1,\"name\": \"craz\",\"email\": \"989798\",\"mobile\": \"crhgjhaz\"}";
        String json2 = "{\"userId\": 1,\"customerId\": 1,\"filter\" : \"name=craz\"}";
        //System.out.println(httpUtilis.post(url,json));
        //System.out.println(httpUtilis.gett(url1,json2));
        List<Integer> string = Arrays.asList(3,5,2,7,5,9);
        IntSummaryStatistics status = string.stream().mapToInt((strings) -> strings ).summaryStatistics();
        System.out.println(status);

    }

}
