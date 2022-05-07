package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSysDictType {
    private String uid;

    private Integer oid;

    private String dictName;

    private String dictType;

    private String createByUid;

    private String updateByUid;

    private String remark;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String isPublish;

    private Integer sort;
}