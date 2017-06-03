package proxyPattern;

import org.junit.Test;

import proxyPattern.service.Image;
import proxyPattern.service.impl.ProxyImage;

public class ProxyImageDemoTest {

	@Test
	public void testProxyImage(){
		String fileName="flower.jpg";
		Image image=new ProxyImage(fileName);
		image.display();	
		System.out.println();
		image.display();
		
	}
}
