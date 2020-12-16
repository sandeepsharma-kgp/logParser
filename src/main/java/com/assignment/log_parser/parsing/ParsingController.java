package com.assignment.log_parser.parsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ParsingController {

    @Autowired
    ParserService parserService;

    @GetMapping("/log_parser")
    public ResponseEntity<Object> getDetails(@RequestParam String filepath) {
        BufferedReader input;
        String line = "";
        StringBuilder result = new StringBuilder("");
        List<LogModel> logModelList = new ArrayList<>();
        String splitBy=",";
        try {
            input = new BufferedReader(new FileReader(filepath));
            input.readLine();
            line = input.readLine();

            while (line != null) {
                String[] log = line.split(splitBy);
                logModelList.add(new LogModel(Long.parseLong(log[0]),log[1],log[2],
                        Float.parseFloat(log[3]),Integer.parseInt(log[4])));
                line = input.readLine();
            }
            List<LogModel> maskedLogs = parserService.maskIntInUrl(logModelList);
            String nativeDir = filepath.substring(0, filepath.lastIndexOf(File.separator));

            String topUrls = parserService.topUrls(maskedLogs, 5);
            String timeComputations = parserService.timeCalc(maskedLogs);

            input.close();
            PrintStream timeUrls = new PrintStream(new FileOutputStream(nativeDir + "/topUrls.csv"));
            PrintStream timeCalc = new PrintStream(new FileOutputStream(nativeDir + "/timeCalc.csv"));
            System.out.println(nativeDir);
            System.out.println(topUrls);
            System.out.println(timeCalc);
            timeUrls.append(topUrls);
            timeUrls.close();
            timeCalc.append(timeComputations);
            timeCalc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}