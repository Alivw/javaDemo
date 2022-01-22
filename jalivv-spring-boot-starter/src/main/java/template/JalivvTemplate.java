package template;

import entity.UserProperties;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/20 19:59
 */
public class JalivvTemplate {

    UserProperties userProperties;

    public void say() {
        System.out.println(userProperties.toString());
        System.out.println("jalivv is so cool!");
    }

    public UserProperties getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }
}
