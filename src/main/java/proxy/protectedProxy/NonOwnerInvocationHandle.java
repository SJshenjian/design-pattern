package proxy.protectedProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandle implements InvocationHandler {
    private PersonBean personBean;

    public NonOwnerInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            }
            if (method.getName().equalsIgnoreCase("setHotOrNotRating")) {
                return method.invoke(personBean, args);
            }
            if (method.getName().startsWith("set")) {
                throw new IllegalAccessException("不能修改别人的隐私信息幺");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
