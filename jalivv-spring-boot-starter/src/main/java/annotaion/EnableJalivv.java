package annotaion;

import org.springframework.context.annotation.Import;
import selector.MyImportSelector;

import java.lang.annotation.*;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/20 20:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyImportSelector.class)
public @interface EnableJalivv {
}
