package com.service;

import com.dao.CourseDAO;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class FindOutTheExchangeRateImpl implements FindOutTheExchangeRate {
    private String source = "https://www.cbr-xml-daily.ru/daily_json.js";
    private String destinition = "data/file.json";

    public CourseDAO getCourse() {
        DownloadJSONFileImpl getJSONFile = new DownloadJSONFileImpl();
        getJSONFile.getJSON(source, destinition);

        return ParserImpl.parseJson(destinition);
    }
}
