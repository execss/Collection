package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbResource {
    private Integer id;

    private String resourceName;

    private String url;

    private String requestMethod;

    private Integer parentId;

    private Boolean isAnonymous;

    private Date createTime;

    private Date updateTime;
}