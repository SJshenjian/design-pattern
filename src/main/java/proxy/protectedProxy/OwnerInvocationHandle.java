package proxy.protectedProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OwnerInvocationHandle implements InvocationHandler {
    private PersonBean personBean;

    public OwnerInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            }
            if (method.getName().equalsIgnoreCase("setHotOrNotRating")) {
                throw new IllegalAccessException("自己不能给自己评分奥");
            }
            if (method.getName().startsWith("set")) {
                return method.invoke(personBean, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
