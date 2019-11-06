package com.ha.file.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pub_area")
@Data
public class PubArea {
    @Id
    private String areaId;

    /**
     * 名称
     */
    private String name;

    /**
     * 父ID
     */
    private String father;


}