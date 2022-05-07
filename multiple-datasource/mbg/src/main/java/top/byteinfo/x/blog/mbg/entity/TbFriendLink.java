package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbFriendLink {
    private Integer id;

    private String linkName;

    private String linkAvatar;

    private String linkAddress;

    private String linkIntro;

    private Date createTime;

    private Date updateTime;
}