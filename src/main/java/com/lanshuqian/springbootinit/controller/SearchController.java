package com.lanshuqian.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanshuqian.springbootinit.common.BaseResponse;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.common.ResultUtils;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.exception.ThrowUtils;
import com.lanshuqian.springbootinit.manager.SearchFacade;
import com.lanshuqian.springbootinit.model.dto.picture.PictureQueryRequest;
import com.lanshuqian.springbootinit.model.dto.post.PostQueryRequest;
import com.lanshuqian.springbootinit.model.dto.search.SearchRequest;
import com.lanshuqian.springbootinit.model.dto.user.UserQueryRequest;
import com.lanshuqian.springbootinit.model.entity.Picture;
import com.lanshuqian.springbootinit.model.enums.SearchTypeEnum;
import com.lanshuqian.springbootinit.model.vo.PostVO;
import com.lanshuqian.springbootinit.model.vo.SearchVO;
import com.lanshuqian.springbootinit.model.vo.UserVO;
import com.lanshuqian.springbootinit.service.PictureService;
import com.lanshuqian.springbootinit.service.PostService;
import com.lanshuqian.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 聚合
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {



    @Resource
    private SearchFacade searchFacade;
    /**
     * 聚合搜索
     *
     * @param searchRequest
     * @param request
     * @return
     */
    @PostMapping("all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest,request));
    }
}
