package main;


public class GameWorld {
	long seed=5234535234553435L;
	SimplexNoise noise = new SimplexNoise((int)seed);

	public double getHeight(double x1, double x2){
		double n21 = (noise.noise(x1, x2)+1)/2;
		n21 *= n21;
		//n21 *= n21;
		return n21*n21-1;
	}
}
