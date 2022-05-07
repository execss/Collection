package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TWebVisit {
    private String uid;

    private String userUid;

    private String ip;

    private String behavior;

    private String moduleUid;

    private String otherData;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String os;

    private String browser;

    private String ipSource;
}