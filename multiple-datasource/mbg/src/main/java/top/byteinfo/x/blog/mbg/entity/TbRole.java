package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbRole {
    private Integer id;

    private String roleName;

    private String roleLabel;

    private Boolean isDisable;

    private Date createTime;

    private Date updateTime;
}