package com.lanshuqian.springbootinit.model.dto.search;

import com.lanshuqian.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequest extends PageRequest implements Serializable {
    private String searchText;

    private String type;

    public static final long serialVersionUID = 1L;
}
