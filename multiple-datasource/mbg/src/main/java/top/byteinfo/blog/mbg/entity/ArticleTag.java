package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleTag {
    private Integer id;

    private Integer articleId;

    private Integer tagId;
}