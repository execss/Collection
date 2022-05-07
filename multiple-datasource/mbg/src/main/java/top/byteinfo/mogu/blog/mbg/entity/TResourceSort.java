package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TResourceSort {
    private String uid;

    private String fileUid;

    private String sortName;

    private String content;

    private String clickCount;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String parentUid;

    private Integer sort;
}