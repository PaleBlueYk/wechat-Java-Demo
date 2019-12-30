package cn.css0209.wechat.service;

import cn.css0209.wechat.config.WeChatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import cn.hutool.crypto.*;

import java.util.Arrays;

/**
 * @author Crated By YangKai On 2019/12/24
 */
@Service
@Slf4j
public class WeChatValidationService {
    private final WeChatConfig config;

    public WeChatValidationService(WeChatConfig config) {
        this.config = config;
    }

    public Mono<String> validation(String signture, String timestamp, String nonce, String echostr) {
        return Mono.just(1).map(n -> {
            String[] checkArr = new String[]{config.getToken(), timestamp, nonce};
            Arrays.sort(checkArr);
            String mySign = SecureUtil.sha1(checkArr[0] + checkArr[1] + checkArr[2]);
            return signChect(signture, mySign, echostr);
        }).doOnError(Throwable::printStackTrace);
    }

    private String signChect(String signture, String mySign, String echostr) {
        log.info("微信signture:{},我的sign:{}", signture, mySign);
        if (signture.equals(mySign)) {
            log.info("接入成功");
            return echostr;
        } else {
            log.info("接入失败");
            return "接入失败";
        }
    }
}
