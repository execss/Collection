package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TBlogSpider {
    private String uid;

    private String title;

    private String summary;

    private String tagUid;

    private Integer clickCount;

    private Integer collectCount;

    private String fileUid;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String adminUid;

    private String isOriginal;

    private String author;

    private String articlesPart;

    private String blogSortUid;

    private Boolean level;

    private String isPublish;

    private Integer sort;

    private String content;
}