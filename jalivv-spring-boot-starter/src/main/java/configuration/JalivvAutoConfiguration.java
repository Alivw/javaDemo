package configuration;

import org.springframework.context.annotation.Bean;
import template.JalivvTemplate;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/20 19:58
 */
public class JalivvAutoConfiguration {

    @Bean
    public JalivvTemplate jalivvTemplate() {
        return new JalivvTemplate();
    }

}
