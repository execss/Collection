package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSubjectItem {
    private String uid;

    private String subjectUid;

    private String blogUid;

    private Boolean status;

    private Integer sort;

    private Date createTime;

    private Date updateTime;
}