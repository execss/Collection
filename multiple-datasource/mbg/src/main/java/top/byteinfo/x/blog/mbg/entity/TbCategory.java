package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbCategory {
    private Integer id;

    private String categoryName;

    private Date createTime;

    private Date updateTime;
}