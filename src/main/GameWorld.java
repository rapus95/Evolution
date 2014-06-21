package main;


public class GameWorld {

	public double getHeight(double x1, double x2){
		return (Math.abs(x1)+5*Math.abs(x2))%40+10;
	}
}
