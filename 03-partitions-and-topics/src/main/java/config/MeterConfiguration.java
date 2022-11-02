package config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This Configuration class is primarily metadata to enable IDEs like
 * IntelliJ to show application specific properties.
 */
@Configuration
@ConfigurationProperties(prefix = "meter")
public class MeterConfiguration {
    private String region;
    private String topic;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
