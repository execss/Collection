package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUserInfo {
    private Integer id;

    private String email;

    private String nickname;

    private String avatar;

    private String intro;

    private String webSite;

    private Boolean isDisable;

    private Date createTime;

    private Date updateTime;
}