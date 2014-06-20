package main;


public class GameWorld {

	public double getHeight(double x1, double x2){
		return (x1+5*x2)%40+10;
	}
}
