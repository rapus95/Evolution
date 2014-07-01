package main;


public class Evolution {

	private static final int x1Start = -10;
	private static final int x1End = 10;
	private static final int x2Start = -10;
	private static final int x2End = 10;
	private static final double stepSize = 0.01;
	
	private GameWorld world = new GameWorld();

	public static void main(String[] args){
		new Evolution();
	}
	
	public Evolution(){
		runGame();
	}
	private double rotation=1;
	public void runGame(){
		Renderer.initWindow(800, 450, "Evolution");
		while(!Renderer.updateWindow()){
			Renderer.renderWorld(world, x1Start, x1End, x2Start, x2End, stepSize, rotation);
			rotation=(rotation+.5)%360;
			//sleep(1);
		}
		Renderer.closeWindow();
	}
	
	private static void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
