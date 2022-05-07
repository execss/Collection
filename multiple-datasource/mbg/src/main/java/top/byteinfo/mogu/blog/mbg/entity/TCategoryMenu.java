package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TCategoryMenu {
    private String uid;

    private String name;

    private Boolean menuLevel;

    private String summary;

    private String parentUid;

    private String url;

    private String icon;

    private Integer sort;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Boolean isShow;

    private Boolean menuType;

    private Boolean isJumpExternalUrl;
}