package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TTag {
    private String uid;

    private String content;

    private Byte status;

    private Integer clickCount;

    private Date createTime;

    private Date updateTime;

    private Integer sort;
}