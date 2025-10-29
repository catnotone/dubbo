package org.huangge.commonutil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(classes = CommonUtilApplicationTests.TestConfig.class)
class CommonUtilApplicationTests {

    @Test
    void contextLoads() {
    }

    @Configuration
    static class TestConfig {
    }
}