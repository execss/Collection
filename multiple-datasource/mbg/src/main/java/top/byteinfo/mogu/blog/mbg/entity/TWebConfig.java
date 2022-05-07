package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TWebConfig {
    private String uid;

    private String logo;

    private String name;

    private String summary;

    private String keyword;

    private String author;

    private String recordNum;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String title;

    private String aliPay;

    private String weixinPay;

    private String github;

    private String gitee;

    private String qqNumber;

    private String qqGroup;

    private String weChat;

    private String email;

    private String showList;

    private String loginTypeList;

    private String openComment;

    private Boolean openMobileComment;

    private Boolean openAdmiration;

    private Boolean openMobileAdmiration;

    private String linkApplyTemplate;
}