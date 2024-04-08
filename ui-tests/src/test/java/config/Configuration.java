package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface Configuration extends Config {
    @Config.Key("baseUrl")
    String baseUrl();
}
