package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbWebsiteConfig {
    private Integer id;

    private String config;

    private Date createTime;

    private Date updateTime;
}