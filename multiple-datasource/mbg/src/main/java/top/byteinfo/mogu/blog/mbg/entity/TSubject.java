package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSubject {
    private String uid;

    private String subjectName;

    private String summary;

    private String fileUid;

    private Integer clickCount;

    private Integer collectCount;

    private Boolean status;

    private Integer sort;

    private Date createTime;

    private Date updateTime;
}