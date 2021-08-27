package jp.co.who.spring_tutorial.api.stream.controller;

import jp.co.who.spring_tutorial.api.stream.helper.AsyncHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
public class StreamController {

    private final AsyncHelper asyncHelper;

    public StreamController(AsyncHelper asyncHelper) {
        this.asyncHelper = asyncHelper;
    }


    @GetMapping("/api/stream")
    public ResponseBodyEmitter streaming() throws Exception {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        asyncHelper.streaming(emitter);

        return emitter;
    }

    @GetMapping("/api/stream2")
    public String streaming2() {
        //var tooLongString = "x" * 100000000000000000000;
        StringBuilder sb = new StringBuilder();
        sb.append("1234567890abcdef".repeat(Math.max(0, 1000000000)));
        return sb.toString();
    }
}
