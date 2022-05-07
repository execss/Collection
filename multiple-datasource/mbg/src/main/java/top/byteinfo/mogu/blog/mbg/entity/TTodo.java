package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TTodo {
    private String uid;

    private String adminUid;

    private String text;

    private Byte done;

    private Byte status;

    private Date createTime;

    private Date updateTime;
}