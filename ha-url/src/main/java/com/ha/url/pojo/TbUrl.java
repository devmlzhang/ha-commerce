package com.ha.url.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author weirdo
 * @since 2020-01-13
 */
@Data
@Table(name = "tb_url")
public class TbUrl implements Serializable {

    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 缩短后的短址id
     */
    @Column(name = "shorl_url_id")
    private String shorlUrlId;

    /**
     * 原网址
     */
    @Column(name = "long_url")
    private String longUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上一次访问时间
     */
    @Column(name = "last_view")
    private Date lastView;

    /**
     * 访问密码
     */
    @Column(name = "view_pwd")
    private String viewPwd;
}
