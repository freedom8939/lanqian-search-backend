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
        postEsDTO.setId(2L);
        postEsDTO.setTitle("海棠的个人简介");
        postEsDTO.setContent("姓名：李四n +年龄：23n +职业：高级程序员n +教育背景：计算机科学与技术本科n +技能：n +精通 Java、Python、C++，熟悉 React、Vue 等前端框架n +扎实的算法与数据结构基础，擅长解决复杂问题n +熟悉分布式系统与微服务架构n 有开源项目贡献经验n");
        postEsDTO.setTags(Arrays.asList("Java", "Python","C++"));
        postEsDTO.setUserId(202321321312L);
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
