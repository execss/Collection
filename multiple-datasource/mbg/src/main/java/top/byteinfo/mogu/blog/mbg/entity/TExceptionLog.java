package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TExceptionLog {
    private String uid;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String ip;

    private String ipSource;

    private String method;

    private String operation;

    private String exceptionJson;

    private String exceptionMessage;

    private String params;
}