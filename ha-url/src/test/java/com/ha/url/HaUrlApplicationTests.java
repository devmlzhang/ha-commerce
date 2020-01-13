package com.ha.url;

import com.ha.url.mapper.TbUrlMapper;
import com.ha.url.pojo.TbUrl;
import com.ha.url.service.TbUrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaUrlApplicationTests {

    @Autowired
    TbUrlMapper urlMapper;


    @Test
    public void name() {
        TbUrl tbUrl = new TbUrl();
        tbUrl.setShorlUrlId("3wd114");
        TbUrl tbUrl1 = urlMapper.selectOne(tbUrl);
        System.out.println(tbUrl1.toString());
    }
}
