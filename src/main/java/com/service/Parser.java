package com.service;

import com.dao.CourseDAO;

public interface Parser {
    public static CourseDAO parseJson(String path){
        return new CourseDAO();
    };
}
