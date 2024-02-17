package com.inicions.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TasksApplication.class)
@ActiveProfiles(value = "test")
class TasksApplicationTests {

	@Test
	void contextLoads() {
	}

}
