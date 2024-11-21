package com.lanshuqian.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanshuqian.springbootinit.model.entity.Picture;

/**
 *
 */
public interface PictureService {

    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

}
