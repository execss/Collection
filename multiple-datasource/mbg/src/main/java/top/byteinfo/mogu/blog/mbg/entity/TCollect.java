package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TCollect {
    private String uid;

    private String userUid;

    private String blogUid;

    private Byte status;

    private Date createTime;

    private Date updateTime;
}