package com.lanshuqian.springbootinit.datasource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.constant.CommonConstant;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.exception.ThrowUtils;
import com.lanshuqian.springbootinit.mapper.PostFavourMapper;
import com.lanshuqian.springbootinit.mapper.PostMapper;
import com.lanshuqian.springbootinit.mapper.PostThumbMapper;
import com.lanshuqian.springbootinit.model.dto.post.PostEsDTO;
import com.lanshuqian.springbootinit.model.dto.post.PostQueryRequest;
import com.lanshuqian.springbootinit.model.entity.Post;
import com.lanshuqian.springbootinit.model.entity.PostFavour;
import com.lanshuqian.springbootinit.model.entity.PostThumb;
import com.lanshuqian.springbootinit.model.entity.User;
import com.lanshuqian.springbootinit.model.vo.PostVO;
import com.lanshuqian.springbootinit.model.vo.UserVO;
import com.lanshuqian.springbootinit.service.PostService;
import com.lanshuqian.springbootinit.service.UserService;
import com.lanshuqian.springbootinit.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 *
 * 
 * 
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {
    @Resource
    private PostService postService;

    @Override
    public Page doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);
        return postService.listPostVOByPage(postQueryRequest,null);

    }
}




