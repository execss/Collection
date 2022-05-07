package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUniqueView {
    private Integer id;

    private Integer viewsCount;

    private Date createTime;

    private Date updateTime;
}