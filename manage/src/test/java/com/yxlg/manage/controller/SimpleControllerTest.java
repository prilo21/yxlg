package com.yxlg.manage.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by amosc on 5/5/2015.
 */
public class SimpleControllerTest {

    @Test
    public void simple() throws Exception {
        standaloneSetup(new SimpleController()).build()
                .perform(get("/simple/simple"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string("Hello world!"));
    }
}
