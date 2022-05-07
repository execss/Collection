package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TRole {
    private String uid;

    private String roleName;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    private String summary;

    private String categoryMenuUids;
}