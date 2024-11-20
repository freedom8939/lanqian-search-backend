package com.lanshuqian.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanshuqian.springbootinit.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author <a href="https://github.com/lilanshuqian">蓝书签</a>
 * @from <a href="https://lanshuqian.icu">编程导航知识星球</a>
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




