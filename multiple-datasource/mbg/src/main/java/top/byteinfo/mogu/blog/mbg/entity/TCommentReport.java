package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TCommentReport {
    private String uid;

    private String userUid;

    private String reportCommentUid;

    private String reportUserUid;

    private String content;

    private Byte progress;

    private Byte status;

    private Date createTime;

    private Date updateTime;
}