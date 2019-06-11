package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author: fate
 * @Date: 2019/5/16 15:37
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplicationTests.class)
@Transactional
@ActiveProfiles("default")
public class BaseTest {

    @Autowired
    public WebApplicationContext context;

    public MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


}
