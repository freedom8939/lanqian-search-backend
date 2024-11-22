package com.lanshuqian.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端的爬虫图片
 */
@Data
public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    String murl;
    String title;
}
