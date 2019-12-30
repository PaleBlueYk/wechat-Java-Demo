package cn.css0209.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Crated By YangKai On 2019/12/24
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {
    private String appid;
    private String secret;
    private String token;
    private String aesKey;
}
