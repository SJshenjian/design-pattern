package bridgePattern.service.impl;

import bridgePattern.service.DrawAPI;

public class GreenCircle implements DrawAPI{

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("[Draw GreenCircle -> radius:"+radius+"x:"+x+"y:"+y+"]");	
	}
}
