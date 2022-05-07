package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbTag {
    private Integer id;

    private String tagName;

    private Date createTime;

    private Date updateTime;
}