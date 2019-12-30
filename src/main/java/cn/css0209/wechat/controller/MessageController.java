package cn.css0209.wechat.controller;

import cn.css0209.wechat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Crated By YangKai On 2019/12/26
 */
@RestController
@Slf4j
@RequestMapping("/")
public class MessageController {
    @Autowired
    private MessageService service;

    @PostMapping(value = "/")
    public Mono<String> getMessage(@RequestBody String messageBody){
        Mono<String> msgMapMono = service.parseMessageBody(messageBody);
        return msgMapMono.doOnNext(log::info);
    }
}
