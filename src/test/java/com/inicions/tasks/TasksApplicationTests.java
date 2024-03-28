package com.inicions.tasks;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TasksApplication.class)
@ActiveProfiles(value = "test")
class TasksApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() throws Exception {
        assertThat(context).isNotNull();
        assertThat(context.containsBean("tasksApplication")).isTrue();
    }

}
