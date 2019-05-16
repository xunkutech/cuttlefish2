package com.example.demo.api.impl;

import com.example.demo.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Locale;

/**
 * @Author: fate
 * @Date: 2019/5/16 15:47
 * @Version 1.0
 */
public class StudentApiImplTest extends BaseTest {

    private String basePath = "/api/v1/students";

    @Before
    public void setUp() {

    }

    @Test
    public void createStudent() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post(basePath )
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .locale(Locale.CHINA)
                .content("{\"name\":\"bob\",\"gender\":\"male\"}")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errMsg").value("OK"))
                .andDo(MockMvcResultHandlers.print());
    }
}