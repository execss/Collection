package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TBlogSort {
    private String uid;

    private String sortName;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    private Integer sort;

    private Integer clickCount;
}