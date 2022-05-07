package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbTalk {
    private Integer id;

    private Integer userId;

    private String content;

    private String images;

    private Boolean isTop;

    private Boolean status;

    private Date createTime;

    private Date updateTime;
}