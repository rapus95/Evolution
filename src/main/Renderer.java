package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;


public class Renderer {

	public static void initWindow(int width, int height, String title){
		/*try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setTitle(title);
			glEnable(GL_TEXTURE_2D);
			glClearColor(1, 1, 1, 0);
			glColor3d(0, 0, 0);
			glLineWidth(10);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glViewport(0, 0, width, height);
			glMatrixMode(GL_MODELVIEW);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glMatrixMode(GL_MODELVIEW);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}*/
	try {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
		Display.setTitle(title);
		Display.setSwapInterval(60);
		//glEnable(GL_POLYGON_SMOOTH);
		//glShadeModel(GL_SMOOTH);
		//glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//glOrtho(-width/100, width/100, -height/100, height/100, 100, 1);
		glOrtho(-width/2, +width/2, 0, height, 0, 100);
		//GLU.gluPerspective(60, width/(float)height, 1, 100);
		//glFrustum(-Main.WIDTH, Main.WIDTH, -Main.HEIGHT, Main.HEIGHT, Main.FRONT_END, Main.BACK_END);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glViewport(0, 0, width, height);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
	} catch (LWJGLException e) {
		e.printStackTrace();
	}
}
	
	public static boolean updateWindow() {
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		return Display.isCloseRequested();
	}
	
	public static void closeWindow() {
		Display.destroy();
	}

	public static void renderWorld(GameWorld world, int x1start, int x1end, int x2start, int x2end, double stepsize) {
		drawCube(0, 500, -5);
		/*for(double x1=x1start; x1<x1end; x1+=stepsize){
			glBegin(GL_LINE_STRIP);
			for(double x2=x2start; x2<x2end; x2+=stepsize){
				glVertex3d(x1, world.getHeight(x1, x2), x2);
			}
			glEnd();
		}*/
	}
	
	private static final int halfLength=50;
	private static void drawCube(float xOffset, float yOffset, float zOffset) {
		glBegin(GL_QUADS);
		//Vorne
		glColor3f(1f, 1f, 0);
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset+halfLength);	//links unten nah
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset+halfLength);	//rechts unten nah
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset+halfLength);	//rechts oben nah
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset+halfLength);	//links oben nah
		//Hinten
		glColor3f(1f, 0, 1f);
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset-halfLength);	//links unten weit
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset-halfLength);	//rechts unten weit
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset-halfLength);	//rechts oben weit
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset-halfLength);	//links oben weit
		//Oben
		glColor3f(0, 1f, 1f);
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset+halfLength);	//rechts oben nah
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset-halfLength);	//rechts oben weit
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset-halfLength);	//links oben weit
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset+halfLength);	//links oben nah
		//Unten
		glColor3f(0, 0, 0);
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset+halfLength);	//rechts unten nah
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset-halfLength);	//rechts unten weit
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset-halfLength);	//links unten weit
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset+halfLength);	//links unten nah
		//Links
		glColor3f(0, 1f, 0);
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset+halfLength);	//links unten nah
		glVertex3d(xOffset-halfLength, yOffset-halfLength, zOffset-halfLength);	//links unten weit
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset-halfLength);	//links oben weit
		glVertex3d(xOffset-halfLength, yOffset+halfLength, zOffset+halfLength);	//links oben nah
		//Rechts
		glColor3f(0, 0, 1f);
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset+halfLength);	//rechts unten nah
		glVertex3d(xOffset+halfLength, yOffset-halfLength, zOffset-halfLength);	//rechts unten weit
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset-halfLength);	//rechts oben weit
		glVertex3d(xOffset+halfLength, yOffset+halfLength, zOffset+halfLength);	//rechts oben nah
		
		glEnd();
	}
	
}
