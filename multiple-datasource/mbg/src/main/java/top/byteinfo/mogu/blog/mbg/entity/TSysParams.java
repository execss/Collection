package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSysParams {
    private String uid;

    private String paramsType;

    private String paramsName;

    private String paramsKey;

    private String remark;

    private String paramsValue;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Integer sort;
}