package main;


public class Vec extends Mat{
	
	public Vec(int dimension){
		super(dimension, 1);
	}
	
	public Vec(double x, double y, double z){
		this(3);
		mat[0][0] = z;
		mat[1][0] = x;
		mat[2][0] = y;
	}
	
	public Vec(double... x){
		this(x.length);
		for(int i=0; i<x.length; i++){
			mat[i][0] = x[i];
		}
	}
	
	public double getX(){
		return mat[1][0];
	}
	
	public double getY(){
		return mat[2][0];
	}
	
	public double getZ(){
		return mat[0][0];
	}
	
	public double dot(Vec other){
		if(this.getRows()!=other.getRows())
			return Double.NaN;
		double tmp=0;
		for(int i=0; i<getRows(); i++){
			tmp+=mat[0][i]*other.mat[0][i];
		}
		return tmp;
	}
	
	public double length() {
		return Math.sqrt(dot(this));
	}
	
	public Vec cross(Vec other) {
		if(this.getRows()!=3 || other.getRows()!=3)
			return null;
		return new Vec(this.getY()*other.getZ()-this.getZ()*other.getY(), this.getZ()*other.getX()-this.getX()*other.getZ(), this.getX()*other.getY()-this.getY()*other.getX());
	}
}
