package com.spring.java.core;

import com.spring.java.common.enums.TestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@Component
public class MockMvcCore {

    @Autowired
    private MockMvc mockMvc;

    public void perform(String path, TestStatus status) {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status.matcher).andDo(MockMvcResultHandlers.print());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}