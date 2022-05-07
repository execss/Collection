package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TStudyVideo {
    private String uid;

    private String fileUid;

    private String resourceSortUid;

    private String name;

    private String summary;

    private String content;

    private String baiduPath;

    private String clickCount;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String parentUid;
}