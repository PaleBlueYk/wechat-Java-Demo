package cn.css0209.wechat.controller;

import cn.css0209.wechat.service.WeChatValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Crated By YangKai On 2019/12/24
 */
@RestController
@RequestMapping("/")
public class WeChatValidationController {
    @Autowired
    private WeChatValidationService servcie;

    /**
     * 微信接入验证
     *
     * @param signature  微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return
     */
    @GetMapping
    public Mono<String> validation(@RequestParam(name = "signature") String signature,
                                   @RequestParam(name = "timestamp") String timestamp,
                                   @RequestParam(name = "nonce") String nonce,
                                   @RequestParam(name = "echostr") String echostr) {
        return servcie.validation(signature, timestamp, nonce, echostr);
    }
}
