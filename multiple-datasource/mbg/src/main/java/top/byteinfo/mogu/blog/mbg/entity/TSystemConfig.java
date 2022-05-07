package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSystemConfig {
    private String uid;

    private String qiNiuAccessKey;

    private String qiNiuSecretKey;

    private String email;

    private String emailUserName;

    private String emailPassword;

    private String smtpAddress;

    private String smtpPort;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String qiNiuBucket;

    private String qiNiuArea;

    private String uploadQiNiu;

    private String uploadLocal;

    private String picturePriority;

    private String qiNiuPictureBaseUrl;

    private String localPictureBaseUrl;

    private String startEmailNotification;

    private Boolean editorModel;

    private String themeColor;

    private String minioEndPoint;

    private String minioAccessKey;

    private String minioSecretKey;

    private String minioBucket;

    private Boolean uploadMinio;

    private String minioPictureBaseUrl;

    private Boolean openDashboardNotification;

    private Boolean contentPicturePriority;

    private Boolean openEmailActivate;

    private Boolean searchModel;

    private String dashboardNotification;
}