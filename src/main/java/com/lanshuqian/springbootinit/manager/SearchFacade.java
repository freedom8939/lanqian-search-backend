package com.lanshuqian.springbootinit.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanshuqian.springbootinit.common.BaseResponse;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.common.ResultUtils;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.exception.ThrowUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class SearchFacade {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    @Resource
    private PostService postService;


    public SearchVO searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);

        String searchText = searchRequest.getSearchText();

        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
        if (searchTypeEnum == null) {

            CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
                UserQueryRequest userQueryRequest = new UserQueryRequest();
                userQueryRequest.setUserName(searchText);
                Page<UserVO> userVOPage = userService.listUserVoByPage(userQueryRequest);
                return userVOPage;
            });

            CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() -> {
                PostQueryRequest postQueryRequest = new PostQueryRequest();
                postQueryRequest.setSearchText(searchText);
                Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
                return postVOPage;
            });

            CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
                Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
                return picturePage;
            });

            CompletableFuture.allOf(userTask, postTask, pictureTask).join();

            Page<UserVO> userVOPage = null;
            try {
                userVOPage = userTask.get();
                Page<PostVO> postVOPage = postTask.get();
                Page<Picture> picturePage = pictureTask.get();

                SearchVO searchVO = new SearchVO();
                searchVO.setUserList(userVOPage.getRecords());
                searchVO.setPostList(postVOPage.getRecords());
                searchVO.setPictureList(picturePage.getRecords());
                return searchVO;
            } catch (Exception e) {
                log.error("查询异常", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
            }

        } else {
            SearchVO searchVO = new SearchVO();

            switch (searchTypeEnum) {
                case POST:
                    PostQueryRequest postQueryRequest = new PostQueryRequest();
                    postQueryRequest.setSearchText(searchText);
                    Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
                    searchVO.setPostList(postVOPage.getRecords());

                    break;
                case USER:
                    UserQueryRequest userQueryRequest = new UserQueryRequest();
                    userQueryRequest.setUserName(searchText);
                    Page<UserVO> userVOPage = userService.listUserVoByPage(userQueryRequest);
                    searchVO.setUserList(userVOPage.getRecords());

                    break;
                case PICTURE:
                    Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
                    searchVO.setPictureList(picturePage.getRecords());
                    break;
                default:
            }
            return searchVO;
        }
    }
}
