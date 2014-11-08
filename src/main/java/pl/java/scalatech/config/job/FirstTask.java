package pl.java.scalatech.config.job;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstTask {

    public void execute() {
        log.info("!!!! hello ");
    }
}