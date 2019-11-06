package com.ha.registry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaRegistryApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException, IOException {
        String bashCommand = "chmod 777 /Users/weirdo/zml/ping.sh";  //①
        Runtime runtime = Runtime.getRuntime();
        Process pro = runtime.exec(bashCommand);  //②
        int status = pro.waitFor();  //③
        if (status != 0){  //④
            System.out.println("restart go server error");
            return;
        }
        System.out.println("restart go server success");
    }

}
