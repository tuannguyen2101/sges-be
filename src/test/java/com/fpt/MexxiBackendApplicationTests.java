package com.fpt;

import com.fpt.repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MexxiBackendApplicationTests {

    @Autowired
    CustomerRepo repo;

    @Test
    void contextLoads() {
        String code = repo.findLastCode();
        Integer id = repo.findLastId();

        String strId = String.valueOf(id + 1);

        StringBuilder builder = new StringBuilder();
        builder.append("KHSGES");
        builder.append("0".repeat(Math.max(0, 8 - strId.length())));
        builder.append(strId);

        log.info("code: " + builder.toString());
    }

}
