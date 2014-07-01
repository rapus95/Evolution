package main;



public class Vec3 {

	public double x;
	public double y;
	public double z;


	public Vec3() {

	}


	public Vec3(double x, double y, double z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(Vec3 vec) {

		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Vec3) {
			Vec3 vec = (Vec3) obj;
			return vec.x == this.x && vec.y == this.y && vec.z == this.z;
		}
		return false;
	}	

	@Override
	public int hashCode() {

		return ((int)this.x) ^ 34 + ((int)this.y) ^ 12 + ((int)this.z);
	}


	@Override
	public String toString() {

		return "Vec3[" + this.x + ", " + this.y + ", " + this.z + "]";
	}

	public void setTo(Vec3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public Vec3 neg(){
		return mul(-1);
	}

	public Vec3 mul(double v) {
		return new Vec3(this.x*v, this.y*v, this.z*v);
	}

	public Vec3 add(Vec3 vec) {
		return new Vec3(this.x+vec.x, this.y+vec.y, this.z+vec.z);
	}

	public Vec3 add(double value) {
		return new Vec3(this.x+value, this.y+value, this.z+value);
	}
	
	public Vec3 sub(Vec3 vec) {
		return new Vec3(this.x-vec.x, this.y-vec.y, this.z-vec.z);
	}
	
	public double distanceTo(Vec3 pos) {
		return sub(pos).length();
	}

	public double length() {
		return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	}


	public Vec3 normalize() {
		double l = length();
		return new Vec3(this.x/l, this.y/l, this.z/l);
	}


	public double dot(Vec3 vec) {
		return this.x*vec.x+this.y*vec.y+this.z*vec.z;
	}

	public Vec3 cross(Vec3 other) {
		return new Vec3(this.y*other.z-this.z*other.y, this.z*other.x-this.x*other.z, this.x*other.y-this.y*other.x);
	}

}
