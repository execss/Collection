package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSysDictData {
    private String uid;

    private Integer oid;

    private String dictTypeUid;

    private String dictLabel;

    private String dictValue;

    private String cssClass;

    private String listClass;

    private Boolean isDefault;

    private String createByUid;

    private String updateByUid;

    private String remark;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String isPublish;

    private Integer sort;
}