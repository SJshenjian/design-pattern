package concurrent.annotation;

import java.lang.annotation.*;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ThreadSafe {
}
