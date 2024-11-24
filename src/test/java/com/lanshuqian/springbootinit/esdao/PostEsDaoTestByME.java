package com.lanshuqian.springbootinit.esdao;

import com.lanshuqian.springbootinit.model.dto.post.PostEsDTO;
import com.lanshuqian.springbootinit.model.dto.post.PostQueryRequest;
import com.lanshuqian.springbootinit.model.entity.Post;
import com.lanshuqian.springbootinit.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 帖子 ES 操作测试
 *
 *
 * 
 */
@SpringBootTest
public class PostEsDaoTestByME {

    @Autowired
    private PostEsDao postEsDao;


    //测试增删改查
    @Test
    void testCreate(){
        PostEsDTO postEsDTO = new PostEsDTO();
        postEsDTO.setId(114514L);
        postEsDTO.setTitle("蓝书签的个人简介");
        postEsDTO.setContent("姓名：张三\n" +
                "年龄：22\n" +
                "职业：高级程序员\n" +
                "教育背景：计算机科学与技术本科\n" +
                "技能：\n" +
                "- 精通 Java、Python、C++，熟悉 React、Vue 等前端框架\n" +
                "- 扎实的算法与数据结构基础，擅长解决复杂问题\n" +
                "- 熟悉分布式系统与微服务架构\n" +
                "- 有开源项目贡献经验");
        postEsDTO.setTags(Arrays.asList("Java", "Python", "C++"));
        postEsDTO.setUserId(202321020167L);
        postEsDTO.setCreateTime(new Date());
        postEsDTO.setUpdateTime(new Date());
        postEsDTO.setIsDelete(0);
        PostEsDTO save = postEsDao.save(postEsDTO);
        System.out.println(save);
    }

    @Test
    void testRead(){
        postEsDao.deleteById(114514L);
    }



}
