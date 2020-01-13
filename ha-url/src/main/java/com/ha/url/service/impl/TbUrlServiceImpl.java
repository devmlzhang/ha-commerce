package com.ha.url.service.impl;

import com.ha.url.mapper.TbUrlMapper;
import com.ha.url.pojo.TbUrl;
import com.ha.url.service.TbUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.Locale;


/**
 * <p>
 *  服务实现类
 * </p>
 * @author weirdo
 * @since 2020-01-13
 */
@Service
public class TbUrlServiceImpl implements TbUrlService {

    @Autowired
    private TbUrlMapper tbUrlMapper;

    @Override
    public int createShortUrl(TbUrl tbUrl) {

        return tbUrlMapper.insert(tbUrl);
    }

    @Override
    public TbUrl findByShortUrlId(String shortUrl) {
        TbUrl url = new TbUrl();
        url.setShorlUrlId(shortUrl);
        return tbUrlMapper.selectOne(url);
    }

    @Override
    public void updateShortUrl(String shorlUrlId) {
        String nowDate = DateUtils.format(new Date(), "yyyy-MM-dd HH-mm-ss", Locale.SIMPLIFIED_CHINESE);
        Example example = new Example(TbUrl.class);
        example.createCriteria().andEqualTo("last_view",nowDate);
        tbUrlMapper.updateByExample(new TbUrl(),example);
    }

    @Override
    public TbUrl findByPwd(String viewPwd, String shortUrlId) {
        return null;
    }
}
