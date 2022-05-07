package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TQuestion {
    private String uid;

    private Integer oid;

    private String title;

    private String summary;

    private String questionTagUid;

    private Integer clickCount;

    private Integer collectCount;

    private String fileUid;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String adminUid;

    private String userUid;

    private String isPublish;

    private Byte questionStatus;

    private Integer sort;

    private Boolean openComment;

    private Integer replyCount;

    private String content;
}