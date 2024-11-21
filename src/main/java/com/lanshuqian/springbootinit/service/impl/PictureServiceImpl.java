package com.lanshuqian.springbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanshuqian.springbootinit.common.ErrorCode;
import com.lanshuqian.springbootinit.exception.BusinessException;
import com.lanshuqian.springbootinit.model.entity.Picture;
import com.lanshuqian.springbootinit.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {

        long current = (pageNum - 1) * pageSize;
        String url =String.format(
                "https://www.bing.com/images/search?q=%s&first=%s" , searchText,current);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据获取异常");
        }
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
            picture.setMurl(murl);
            picture.setTitle(title);
            pictures.add(picture);
            if(pictures.size() >= pageSize){
                break;
            }
        }
        Page<Picture> picturePage = new Page<>(pageNum,pageSize);
        picturePage.setRecords(pictures);
       return picturePage;
    }
}
