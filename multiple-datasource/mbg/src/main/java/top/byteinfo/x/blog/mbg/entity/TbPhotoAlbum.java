package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbPhotoAlbum {
    private Integer id;

    private String albumName;

    private String albumDesc;

    private String albumCover;

    private Boolean isDelete;

    private Boolean status;

    private Date createTime;

    private Date updateTime;
}