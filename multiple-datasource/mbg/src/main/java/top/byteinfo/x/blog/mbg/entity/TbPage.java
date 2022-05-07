package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbPage {
    private Integer id;

    private String pageName;

    private String pageLabel;

    private String pageCover;

    private Date createTime;

    private Date updateTime;
}