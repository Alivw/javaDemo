package configuration;

import entity.UserProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import template.JalivvTemplate;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/20 19:58
 */
@Configuration
@ConditionalOnClass(Jedis.class)
@EnableConfigurationProperties(UserProperties.class)
@ConditionalOnProperty(prefix = "spring.jalivv", value = "name", matchIfMissing = true)
public class JalivvAutoConfiguration {

    @Bean
    public JalivvTemplate jalivvTemplate(UserProperties userProperties) {
        JalivvTemplate jalivvTemplate = new JalivvTemplate();
        jalivvTemplate.setUserProperties(userProperties);
        return jalivvTemplate;
    }

}
