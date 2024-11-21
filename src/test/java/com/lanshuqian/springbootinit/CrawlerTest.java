package com.lanshuqian.springbootinit;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.lanshuqian.springbootinit.model.entity.Picture;
import com.lanshuqian.springbootinit.model.entity.Post;
import com.lanshuqian.springbootinit.service.PostService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.annotation.Documented;
import java.util.*;

@SpringBootTest
public class CrawlerTest {


    private static final Logger log = LoggerFactory.getLogger(CrawlerTest.class);
    @Resource
    private PostService postService;


    @SneakyThrows
    @Test
    void fetchPicture(){
        int current = 1;
        String url =
                "https://www.bing.com/images/search?q=小黑子&first=%s" + current;
        Document doc = Jsoup.connect(url).get();
        List<Picture> pictures = new ArrayList<>();  //图片
        Elements elements = doc.select(".iuscp.isv");
        for (Element element : elements) {
            //取图片地址 murl
            String m = element.select(".iusc").get(0).attr("m");
            //取标题
            Map map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            String title = element.select(".inflnk").get(0).attr("aria-label");
            Picture picture = new Picture();
            picture.setMrul(murl);
            picture.setTitle(title);
            pictures.add(picture);
        }
        System.out.println(pictures);
    }

    @Test
    void fetchPassage(){
        String json = "{\"pageSize\":12,\"sortOrder\":\"descend\",\"sortField\":\"createTime\",\"tags\":[],\"current\":1,\"reviewStatus\":1,\"category\":\"文章\",\"hiddenContent\":true}\n";
        String url = "https://api.codefather.cn/api/post/list/page/vo";
        String result = HttpRequest.post(url)
                .body(json)
                .execute().body();
        //json解析成对象
        Map<String,Object>  map = JSONUtil.toBean(result, Map.class);
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
        Assertions.assertTrue(res);
    }
}
