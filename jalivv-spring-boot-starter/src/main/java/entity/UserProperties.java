package entity;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description UserProperties
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/22 08:19
 */
@ConfigurationProperties(prefix = "spring.jalivv")
public class UserProperties {

    private String name;

    private Integer age;

    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
