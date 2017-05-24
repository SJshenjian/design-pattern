package bridgePattern.service.impl;

import bridgePattern.service.DrawAPI;

public class RedCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("[Draw RedCircle -> radius:"+radius+"x:"+x+"y:"+y+"]");	
	}
}
