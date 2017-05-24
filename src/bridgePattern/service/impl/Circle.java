package bridgePattern.service.impl;

import bridgePattern.service.DrawAPI;
import bridgePattern.service.Shape;

public class Circle  extends Shape{

	private int radius;
	private int x;
	private int y;
	
	public Circle(int radius,int x,int y,DrawAPI drawAPI) {
		super(drawAPI);
		this.radius=radius;
		this.x=x;
		this.y=y;
	}

	public void draw(){
		drawAPI.drawCircle(radius, x, y);
	}
}
