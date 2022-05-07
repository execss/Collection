package top.byteinfo.x.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbRoleMenu {
    private Integer id;

    private Integer roleId;

    private Integer menuId;
}