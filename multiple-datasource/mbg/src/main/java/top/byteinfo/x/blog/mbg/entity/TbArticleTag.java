package top.byteinfo.x.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbArticleTag {
    private Integer id;

    private Integer articleId;

    private Integer tagId;
}