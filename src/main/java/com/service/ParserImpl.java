package com.service;

import com.dao.CourseDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ParserImpl {
    public static CourseDAO parseJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(path), CourseDAO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
