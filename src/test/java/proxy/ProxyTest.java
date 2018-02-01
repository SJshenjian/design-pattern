package proxy;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import proxy.protectedProxy.PersonBean;
import proxy.protectedProxy.PersonProxy;
import proxy.protectedProxy.PersonalBeanImpl;
import proxy.remoteProxy.service.GumballMachineMonitor;
import proxy.remoteProxy.service.GumballMachineRemote;
import proxy.virtualProxy.ImageComponent;
import proxy.virtualProxy.ImageProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageConsumer;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.util.Enumeration;
import java.util.Hashtable;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class ProxyTest {

    @Test
    public void testRemoteProxy() {
        //需先完成Head First设计模式对应步骤
        String[] locations = {"rmi://127.0.0.1/www.baidu.com", "rmi://127.0.0.1/www.qq.com", "rmi://127.0.0.1/www.alibaba.com", };
        for (String location : locations) {
            try {
                GumballMachineRemote service = (GumballMachineRemote) Naming.lookup(location);
                GumballMachineMonitor monitor = new GumballMachineMonitor(service);
                monitor.report();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testVirtualProxy() throws MalformedURLException {
        //涉及到面板不进行测试研究
    }

    @Test
    public void testProtectedProxy(){
        PersonBean person = new PersonalBeanImpl();

        PersonBean ownerPerson = PersonProxy.getOwnerPersonProxy(person);
        ownerPerson.setName("shenjian");
        ownerPerson.setGender("boy");
        ownerPerson.setInterests("IT");
        assertEquals("shenjian", ownerPerson.getName());
        assertEquals("boy", ownerPerson.getGender());
        assertEquals("IT", ownerPerson.getInterests());
        try {
            ownerPerson.setHotOrNotRating(95);
        } catch (Exception e) {
            assertEquals("自己不能给自己评分奥", e.getMessage());
        }

        PersonBean nonOwnerPerson = PersonProxy.getNonPersonProxy(person);
        assertEquals("shenjian", nonOwnerPerson.getName());
        assertEquals("boy", nonOwnerPerson.getGender());
        assertEquals("IT", nonOwnerPerson.getInterests());

        nonOwnerPerson.setHotOrNotRating(95);
        assertEquals(95, nonOwnerPerson.getHotOrNotRating());
        try {
            nonOwnerPerson.setGender("女");
        } catch (Exception e) {
            assertEquals("不能修改别人的隐私信息幺", e.getMessage());
        }

    }
}
