package main;

import java.util.Arrays;


public class Mat implements Cloneable{

	protected double mat[][];
	
	public Mat(int rows, int colums){
		mat = new double[rows][colums];
	}
	
	public Mat(Mat m){
		mat = new double[m.getRows()][m.getColums()];
		for(int row=0; row<mat.length; row++){
			System.arraycopy(m.mat[row], 0, mat[row], 0, mat[row].length);
		}
	}
	
	public static void setIdentity(Mat m){
		m.setIdentity();
	}
	
	public static void setIdentity(Mat m, double scale){
		m.setIdentity(scale);
	}
	
	public void setIdentity(){
		setIdentity(1);
	}
	
	public void setIdentity(double scale){
		for(int row=0; row<getRows(); row++){
			Arrays.fill(mat[row], 0);
			if(mat[row].length<row)
				mat[row][row] = scale;
		}
	}
	
	public int getRows(){
		return mat.length;
	}
	
	public int getColums(){
		return mat[0].length;
	}
	
	public Mat rotate(double degree){
		return null;
	}
	
	public static Mat makeRotMat(double degree, double...axis){
		Mat m = new Mat(axis.length, axis.length);
		if(degree!=0)
			throw new UnsupportedOperationException("Not yet implemented!!!!");
		return m;
	}
	
	public static Mat mul(Mat m1, Mat m2){
		return m1.mul(m2);
	}
	
	public Mat mul(Mat other){
		int 
		col1 = getColums(),
		row1 = getRows(),
		col2 = other.getColums(),
		row2 = other.getRows();
		if(col1!=row2)
			throw new IllegalArgumentException("can't multiply" + row1 + "x" + col1 + " with " + row2 + "x" + col2 + ".");
		Mat dest = new Mat(row1, col2);
		for(int destRow=0; destRow<row1; destRow++){
			for(int i=0; i<col1; i++){
				for(int destCol=0; destCol<col2; destCol++){
					dest.mat[destRow][destCol] += mat[destRow][i] * other.mat[i][destCol];
				}
			}
		}
		return dest;
	}
	
	public static Mat mul(Mat m1, double val){
		return m1.mul(val);
	}
	
	public Mat mul(double val){
		Mat dest = new Mat(this);
		for(int row=0; row<getRows(); row++){
			for(int col=0; col<getColums(); col++){
				dest.mat[row][col] *= val;
			}
		}
		return dest;
	}

	@Override
	public Mat clone() {
		return new Mat(this);
	}
	
}
