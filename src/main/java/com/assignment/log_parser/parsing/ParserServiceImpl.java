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

}
