package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TPicture {
    private String uid;

    private String fileUid;

    private String picName;

    private String pictureSortUid;

    private Byte status;

    private Date createTime;

    private Date updateTime;
}