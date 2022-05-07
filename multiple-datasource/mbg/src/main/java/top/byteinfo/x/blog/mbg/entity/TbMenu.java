package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbMenu {
    private Integer id;

    private String name;

    private String path;

    private String component;

    private String icon;

    private Date createTime;

    private Date updateTime;

    private Boolean orderNum;

    private Integer parentId;

    private Boolean isHidden;
}