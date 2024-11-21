package com.lanshuqian.springbootinit.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lanshuqian.springbootinit.esdao.PostEsDao;
import com.lanshuqian.springbootinit.model.dto.post.PostEsDTO;
import com.lanshuqian.springbootinit.model.entity.Post;
import com.lanshuqian.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取初始帖子列表
 */
// 取消注释后，每次启动springboot项目的时候 会启动一次run方法
//@Component
@Slf4j
public class FetchinitPostList implements CommandLineRunner {

    @Resource
    private PostService postService;


    @Override
    public void run(String... args) throws Exception {
        String json = "{\"pageSize\":12,\"sortOrder\":\"descend\",\"sortField\":\"createTime\",\"tags\":[],\"current\":1,\"reviewStatus\":1,\"category\":\"文章\",\"hiddenContent\":true}\n";
        String url = "https://api.codefather.cn/api/post/list/page/vo";
        String result = HttpRequest.post(url)
                .body(json)
                .execute().body();
        //json解析成对象
        Map<String,Object> map = JSONUtil.toBean(result, Map.class);
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records = (JSONArray) data.get("records");
        List<Post> postList = new ArrayList<>();
        for (Object record : records) {
            JSONObject temp = (JSONObject) record;
            Post post = new Post();

            post.setTitle(temp.getStr("title"));
            post.setContent(temp.getStr("plainTextDescription"));
            JSONArray tags =(JSONArray) temp.get("tags");
            List<String> tagList =  tags.toList(String.class);

            post.setTags(JSONUtil.toJsonStr(tagList));
            post.setUserId(1L);
            post.setCreateTime(new Date());
            post.setUpdateTime(new Date());

            postList.add(post);
        }
        System.out.println(postList);

        boolean res = postService.saveBatch(postList);
        if (res){
            log.info("初始化帖子列表成功");
        }else{
            log.error("初始化帖子失败");
        }
    }
}
