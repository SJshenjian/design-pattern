package proxy.protectedProxy;

import java.lang.reflect.Proxy;

public class PersonProxy {

    public static PersonBean getOwnerPersonProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new OwnerInvocationHandle(personBean) );
    }

    public static PersonBean getNonPersonProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new NonOwnerInvocationHandle(personBean));
    }
}
