package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TFeedback {
    private String uid;

    private String userUid;

    private String content;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String title;

    private Boolean feedbackStatus;

    private String reply;

    private String adminUid;
}