package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Renderer {

	private static final Vec3 lightVec = new Vec3(-1, -1, -1);
	private static int list;
	
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
//		glOrtho(-width/2, +width/2, -height/2, +height/2, 0, -1000);
		glViewport(0, 0, width, height);
		GLU.gluPerspective(45.0f,width/(float)height,0.1f,5000.0f);
//		GLU.gluLookAt(50, 50, -50, 50, 50, 0, 0, 1, 0);
//		glFrustum(-width/2, +width/2, -height/2, +height/2, 0, 1000);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glClearColor(0.1f, 0.1f, 1.0f, 1.0f);
		glColor4f(0,0,0,1);
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
		GL11.glDeleteLists(list, 1);
		Display.destroy();
	}
	
	public static void renderClippingPlanes(){
		glBegin(GL_QUADS);
		
		//Vorne
		glColor4d(0, 0.5, 0.5, 0.5);
		glVertex3d(-200, -112.5, 0);	//links unten nah
		glVertex3d(+200, -112.5, 0);	//rechts unten nah
		glVertex3d(+200, +112.5, 0);	//rechts oben nah
		glVertex3d(-200, +112.5, 0);	//links oben nah

		glColor4d(0, 0, 0, 0.5);
		glVertex3d(-200, -112.5, -100);	//links unten fern
		glVertex3d(+200, -112.5, -100);	//rechts unten fern
		glVertex3d(+200, +112.5, -100);	//rechts oben fern
		glVertex3d(-200, +112.5, -100);	//links oben fern
		glEnd();
	}

	public static void renderWorld(GameWorld world, int x1start, int x1end, int x2start, int x2end, double stepsize, double rot) {
		glPushMatrix();
//		glLoadIdentity();
		//drawCube(0, 500, 50);
		//GL11.glLineWidth(100);
//		x1start = 50;
//		x1end = 100;
//		x2start = 0;
//		x2end = 50;
//		stepsize=50;
//		int yOffset=500;
		glTranslated(0, 0, -200);
		glRotated(15, 1, 0, 0);
		glRotated(rot, 0, 1, 0);
		glScaled(100, 100, 100);
		glColor3d(0, 0, 0);
		if(list==0){
			list = GL11.glGenLists(1);
			GL11.glNewList(list, GL11.GL_COMPILE);
			for(double x2=x2start; x2<x2end; x2+=stepsize){
				glBegin(currMode=GL11.GL_QUADS);
				for(double x1=x1start; x1<x1end; x1+=stepsize){
					renderEasyMesh(world, x2, x1, stepsize);
//					glVertex3d(x2, world.getHeight(x1, x2), x1);
//					glVertex3d(x2+stepsize, world.getHeight(x1, x2), x1);
//					System.out.println(x2 +":"+ world.getHeight(x1, x2) +":"+ x1);
				}
				glEnd();
			}
			GL11.glEndList();
		}
		GL11.glCallList(list);
//		renderClippingPlanes();
		glPopMatrix();
	}

	
	private static int currMode = 0;
	private static void renderEasyMesh(GameWorld w, double x2, double x1, double step) {
		double p1 = w.getHeight(x2, x1);
		double p2 = w.getHeight(x2+step, x1);
		double p3 = w.getHeight(x2, x1+step);
		double p4 = w.getHeight(x2+step, x1+step);
		
		Vec3 v1 = new Vec3(x2, p1, x1);
		Vec3 v2 = new Vec3(x2+step, p2, x1);
		Vec3 v3 = new Vec3(x2, p3, x1+step);
		v2 = v2.sub(v1);
		v3 = v3.sub(v1);
		v1 = v2.cross(v3).normalize();
		double d = v1.dot(lightVec);
		if(d<0){
			d=0;
		}
		d = d*0.9+0.1;
		glColor3d(d, d, d);
		glVertex3d(x2+step, p2, x1);
		
		glVertex3d(x2, p1, x1);
		
		if(currMode == GL11.GL_LINES) {
			glVertex3d(x2, p1, x1);
		}
		
		glVertex3d(x2, p3, x1+step);
		
		if(currMode == GL11.GL_QUADS) {
			glVertex3d(x2+step, p4, x1+step);
		}
	}
	
	private static final int halfLength=50;
	private static void drawCube(float xOffset, float yOffset, float zOffset) {
		glBegin(GL_LINE_LOOP);
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
