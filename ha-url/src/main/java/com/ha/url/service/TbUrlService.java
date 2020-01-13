package com.ha.url.service;


import com.ha.url.pojo.TbUrl;

/**
 * <p>
 *  服务类
 * </p>
 * @author weirdo
 * @since 2020-01-13
 */
public interface TbUrlService {

    int createShortUrl(TbUrl tbUrl);

    TbUrl findByShortUrlId(String shortUrl);

    void updateShortUrl(String shorlUrlId);

    TbUrl findByPwd(String viewPwd, String shortUrlId);

}
