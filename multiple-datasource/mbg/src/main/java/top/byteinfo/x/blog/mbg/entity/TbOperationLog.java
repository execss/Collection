package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbOperationLog {
    private Integer id;

    private String optModule;

    private String optType;

    private String optUrl;

    private String optMethod;

    private String optDesc;

    private String requestMethod;

    private Integer userId;

    private String nickname;

    private String ipAddress;

    private String ipSource;

    private Date createTime;

    private Date updateTime;

    private String requestParam;

    private String responseData;
}