package com.assignment.log_parser.parsing;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParserServiceImpl implements ParserService {

    private static final String regex = "-?\\d+(\\.\\d+)?";

    @Override
    public List<LogModel> maskIntInUrl(List<LogModel> logModels) {

        List<LogModel> newLogList = new ArrayList<>();
        for(LogModel logModel : logModels) {
            String newPath = logModel.getUrl().replaceAll(regex,"{id}");
            logModel.setUrl(newPath);
            newLogList.add(logModel);
        }
        return newLogList;
    }

    @Override
    public String topUrls(List<LogModel> maskedLogs, int topCount) {
        HashMap<MethodUrlMap,Integer> urlMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for(LogModel logModel : maskedLogs) {
            MethodUrlMap methodUrlPair = new MethodUrlMap(logModel.getMethod(),logModel.getUrl());

            if(urlMap.containsKey(methodUrlPair)) {
                urlMap.put(methodUrlPair,urlMap.get(methodUrlPair) + 1);
            } else {
                urlMap.put(methodUrlPair, 1);
            }
        }
        int n = Math.min(topCount, urlMap.size());

        result.append("Method,URL,Frequency\n");

        urlMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry->{
                    result.append(entry.getKey().getMethod())
                            .append(",")
                            .append(entry.getKey().getUrl())
                            .append(",")
                            .append(entry.getValue())
                            .append("\n");
                });
        StringBuilder finalResult = new StringBuilder();

        String[] res = result.toString().split("\n");
        for (int i=0;i<=n;i++) {
            finalResult.append(res[i]);
            finalResult.append("\n");
        }
        return finalResult.toString();
    }

    @Override
    public String timeCalc(List<LogModel> maskedLogs) {
        HashMap<MethodUrlMap,LogTimeModel> urlTimeMap = new HashMap<>();

        StringBuilder result = new StringBuilder();
        for(LogModel logModel : maskedLogs) {
            MethodUrlMap methodUrlPair = new MethodUrlMap(logModel.getMethod(),logModel.getUrl());

            if(urlTimeMap.containsKey(methodUrlPair)) {
                LogTimeModel logTimeModel = urlTimeMap.get(methodUrlPair);
                Float currMin = logTimeModel.getMinTime();
                Float currMax = logTimeModel.getMaxTime();
                if(logModel.getResponse_time()<currMin) {
                    logTimeModel.setMinTime(logModel.getResponse_time());
                }
                if(logModel.getResponse_time()>currMax) {
                    logTimeModel.setMaxTime(logModel.getResponse_time());
                }
                logTimeModel.setTotalTime(logTimeModel.getTotalTime() + logModel.getResponse_time());
                logTimeModel.setCount(logTimeModel.getCount()+1);
            } else {
                urlTimeMap.put(methodUrlPair,new LogTimeModel(logModel.getResponse_time()));
            }
        }


        result.append("Method," +
                "URL," +
                "Min Time," +
                "Max Time," +
                "Average Time\n");

        urlTimeMap.forEach((key, value) -> result.append(key.getMethod())
                .append(",").append(key.getUrl())
                .append(",").append(value.getMinTime())
                .append(",").append(value.getMaxTime())
                .append(",").append(value.getTotalTime() / value.getCount())
                .append("\n"));


        return result.toString();
    }

}
