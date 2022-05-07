package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TPictureSort {
    private String uid;

    private String fileUid;

    private String name;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String parentUid;

    private Integer sort;

    private Boolean isShow;
}