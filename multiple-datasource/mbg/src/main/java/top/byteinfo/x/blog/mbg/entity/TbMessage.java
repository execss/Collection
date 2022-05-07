package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbMessage {
    private Integer id;

    private String nickname;

    private String avatar;

    private String messageContent;

    private String ipAddress;

    private String ipSource;

    private Boolean time;

    private Byte isReview;

    private Date createTime;

    private Date updateTime;
}