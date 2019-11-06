package com.ha.user.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_resource")
public class SysResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 父菜单ID
     */
    @Column(name = "p_id")
    private String pId;

    /**
     * 名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 是否是菜单
     */
    @Column(name = "is_menu")
    private String isMenu;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 菜单排序
     */
    private Integer sort;

    private String status;

    private String icon;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取菜单编码
     *
     * @return code - 菜单编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置菜单编码
     *
     * @param code 菜单编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取父菜单ID
     *
     * @return p_id - 父菜单ID
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置父菜单ID
     *
     * @param pId 父菜单ID
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * 获取名称
     *
     * @return menu_name - 名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置名称
     *
     * @param menuName 名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取请求地址
     *
     * @return url - 请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置请求地址
     *
     * @param url 请求地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取是否是菜单
     *
     * @return is_menu - 是否是菜单
     */
    public String getIsMenu() {
        return isMenu;
    }

    /**
     * 设置是否是菜单
     *
     * @param isMenu 是否是菜单
     */
    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * 获取菜单层级
     *
     * @return level - 菜单层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置菜单层级
     *
     * @param level 菜单层级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取菜单排序
     *
     * @return sort - 菜单排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置菜单排序
     *
     * @param sort 菜单排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}