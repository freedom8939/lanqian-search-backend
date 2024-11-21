package com.lanshuqian.springbootinit.model.dto.picture;

import com.lanshuqian.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class PictureQueryRequest extends PageRequest implements Serializable {
    private String searchText;

    public static final long serialVersionUID = 1L;
}
