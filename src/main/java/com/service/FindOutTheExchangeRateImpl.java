package com.service;

import com.dao.CourseDAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class FindOutTheExchangeRateImpl implements FindOutTheExchangeRate {
    private DownloadJSONFileImpl getJSONFile;
    private String source = "https://www.cbr-xml-daily.ru/daily_json.js";
    private String destinition = "data/file.json";

    @Autowired
    public void setGetJSONFile(DownloadJSONFileImpl getJSONFile) {
        this.getJSONFile = getJSONFile;
    }

    public CourseDAO getCourse() {
        getJSONFile.getJSON(source, destinition);

        return ParserImpl.parseJson(destinition);
    }
}
