package main;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;


public class VBO {

	private int id;
	private ByteBuffer buffer;
	
	public VBO(){

	}
	
	public VBO(long size, int usage){
		alloc(size, usage);
	}
	
	public void alloc(long size, int usage){
		free();
		id = ARBVertexBufferObject.glGenBuffersARB();
		bind();
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, size, usage);
	}
	
	public ByteBuffer letWrite(){
		if(buffer!=null)
			return buffer;
		bind();
		return buffer = ARBVertexBufferObject.glMapBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, ARBVertexBufferObject.GL_WRITE_ONLY_ARB, null);
	}
	
	public void stopWrite(){
		buffer = null;
		ARBVertexBufferObject.glUnmapBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB);
	}
	
	public void free(){
		buffer=null;
		if(id!=0)
			ARBVertexBufferObject.glDeleteBuffersARB(id);
	}
	
	public void render(int typeSize, int size){
		bind();
		//ARBVertexBufferObject.glInterleavedArrays(ARBVertexBufferObject.GL_T2F_V3F, typeSize, null);
		GL11.glDrawArrays(GL11.GL_QUADS, 0, size);
	}
	
	public void bind(){
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, id);
	}
	
	public static void unbind(){
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, 0);
	}

	@Override
	protected void finalize() {
		free();
	}
	
}
