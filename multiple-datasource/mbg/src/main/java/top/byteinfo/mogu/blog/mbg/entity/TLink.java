package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TLink {
    private String uid;

    private String title;

    private String summary;

    private String url;

    private Integer clickCount;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    private Integer sort;

    private Boolean linkStatus;

    private String userUid;

    private String adminUid;

    private String email;

    private String fileUid;
}