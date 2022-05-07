package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbChatRecord {
    private Integer id;

    private Integer userId;

    private String nickname;

    private String avatar;

    private String content;

    private String ipAddress;

    private String ipSource;

    private Byte type;

    private Date createTime;

    private Date updateTime;
}