package com.lanshuqian.springbootinit.datasource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.constant.CommonConstant;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.mapper.UserMapper;
import com.lanshuqian.springbootinit.model.dto.user.UserQueryRequest;
import com.lanshuqian.springbootinit.model.entity.User;
import com.lanshuqian.springbootinit.model.enums.UserRoleEnum;
import com.lanshuqian.springbootinit.model.vo.LoginUserVO;
import com.lanshuqian.springbootinit.model.vo.UserVO;
import com.lanshuqian.springbootinit.service.UserService;
import com.lanshuqian.springbootinit.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lanshuqian.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现
 *
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setPageSize(pageSize);
        userQueryRequest.setCurrent(pageNum);
        return userService.listUserVoByPage(userQueryRequest);
    }
}
