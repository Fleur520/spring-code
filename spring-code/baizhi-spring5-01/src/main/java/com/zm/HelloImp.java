package com.zm;

import com.zm.anno.HelloInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author minzhang
 * @date 2022/03/13 19:08
 **/
@Slf4j
@Component
public class HelloImp implements HelloInterface {
    @Override
    public void sayHello() {
      log.info("======aaaa=======");
    }
}
