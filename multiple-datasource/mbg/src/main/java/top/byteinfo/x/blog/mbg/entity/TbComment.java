package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbComment {
    private Integer id;

    private Integer userId;

    private Integer topicId;

    private Integer replyUserId;

    private Integer parentId;

    private Byte type;

    private Byte isDelete;

    private Boolean isReview;

    private Date createTime;

    private Date updateTime;

    private String commentContent;
}