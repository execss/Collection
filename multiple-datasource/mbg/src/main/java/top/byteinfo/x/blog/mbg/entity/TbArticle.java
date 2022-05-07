package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbArticle {
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String articleCover;

    private String articleTitle;

    private Boolean type;

    private String originalUrl;

    private Boolean isTop;

    private Boolean isDelete;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String articleContent;
}