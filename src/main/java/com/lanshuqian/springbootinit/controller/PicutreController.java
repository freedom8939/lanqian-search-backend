package com.lanshuqian.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.lanshuqian.springbootinit.annotation.AuthCheck;
import com.lanshuqian.springbootinit.common.BaseResponse;
import com.lanshuqian.springbootinit.common.DeleteRequest;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.common.ResultUtils;
import com.lanshuqian.springbootinit.constant.UserConstant;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.exception.ThrowUtils;
import com.lanshuqian.springbootinit.model.dto.picture.PictureQueryRequest;
import com.lanshuqian.springbootinit.model.dto.post.PostAddRequest;
import com.lanshuqian.springbootinit.model.dto.post.PostEditRequest;
import com.lanshuqian.springbootinit.model.dto.post.PostQueryRequest;
import com.lanshuqian.springbootinit.model.dto.post.PostUpdateRequest;
import com.lanshuqian.springbootinit.model.entity.Picture;
import com.lanshuqian.springbootinit.model.entity.Post;
import com.lanshuqian.springbootinit.model.entity.User;
import com.lanshuqian.springbootinit.model.vo.PostVO;
import com.lanshuqian.springbootinit.service.PictureService;
import com.lanshuqian.springbootinit.service.PostService;
import com.lanshuqian.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图片接口
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PicutreController {

    @Resource
    private PictureService pictureService;



    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();

        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);

        return ResultUtils.success(picturePage);
    }

}
