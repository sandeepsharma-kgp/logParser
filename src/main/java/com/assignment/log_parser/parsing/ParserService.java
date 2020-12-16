package com.assignment.log_parser.parsing;

import java.util.List;

public interface ParserService {

    List<LogModel> maskIntInUrl(List<LogModel> logModels);

    String topUrls(List<LogModel> maskedLogs, int topCount);
}
