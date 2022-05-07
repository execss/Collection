package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TWebNavbar {
    private String uid;

    private String name;

    private Boolean navbarLevel;

    private String summary;

    private String parentUid;

    private String url;

    private String icon;

    private Boolean isShow;

    private Boolean isJumpExternalUrl;

    private Integer sort;

    private Boolean status;

    private Date createTime;

    private Date updateTime;
}