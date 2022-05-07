package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TComment {
    private String uid;

    private String userUid;

    private String toUid;

    private String toUserUid;

    private String content;

    private String blogUid;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String source;

    private Boolean type;

    private String firstCommentUid;
}