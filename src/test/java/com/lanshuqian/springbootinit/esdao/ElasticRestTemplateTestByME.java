package com.lanshuqian.springbootinit.esdao;

import com.lanshuqian.springbootinit.model.dto.post.PostEsDTO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Arrays;
import java.util.Date;

/**
 * 帖子 ES 操作测试
 *
 *
 * 
 */
@SpringBootTest
public class ElasticRestTemplateTestByME {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Test
    void get(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 按标题检索
        if (StringUtils.isNotBlank("aa")) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("title", "个人简介"));
            boolQueryBuilder.minimumShouldMatch(1);
        }


        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
                .build();

        SearchHits<PostEsDTO> result = restTemplate.search(searchQuery, PostEsDTO.class);
        System.out.println(result.getSearchHits());
    }





}
