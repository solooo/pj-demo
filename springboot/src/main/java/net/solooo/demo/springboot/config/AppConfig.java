package net.solooo.demo.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Eric on 2017/3/25.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setName(String name) {
        this.name = name;
    }
}
