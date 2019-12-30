package cn.css0209.wechat.service;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Crated By YangKai On 2019/12/26
 */
@Service
@Slf4j
public class MessageService {
    public Mono<String> parseMessageBody(String messageBody) {
        return Mono.just(messageBody).map(m -> {
            Map<String, String> messageMap = new HashMap<>(0);
            SAXReader reader = new SAXReader();
            try {
                Document document = reader.read(new ByteArrayInputStream(m.getBytes(StandardCharsets.UTF_8)));
                Element root = document.getRootElement();
                List<Element> elementList = root.elements();
                elementList.forEach(e -> messageMap.put(e.getName(), e.getStringValue()));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            return returnMsg(messageMap);
        }).doOnError(Throwable::printStackTrace);
    }

    private String returnMsg(Map<String, String> msg){
        log.info(msg.toString());
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        Element toUserName = root.addElement("ToUserName");
        Element fromUserName = root.addElement("FromUserName");
        Element createTime = root.addElement("CreateTime");
        Element msgType = root.addElement("MsgType");
        Element content = root.addElement("Content");
        toUserName.addCDATA(msg.get("FromUserName"));
        fromUserName.addCDATA(msg.get("ToUserName"));
        createTime.addText(String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        msgType.addCDATA("text");
        content.addCDATA("hello");
        return root.asXML();
    }
}
