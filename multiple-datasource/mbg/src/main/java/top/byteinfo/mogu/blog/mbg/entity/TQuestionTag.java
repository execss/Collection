package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TQuestionTag {
    private String uid;

    private String parentUid;

    private String name;

    private String summary;

    private Byte status;

    private Integer clickCount;

    private Date createTime;

    private Date updateTime;

    private Integer sort;
}