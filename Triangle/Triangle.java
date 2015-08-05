class Triangle {
	double area;
	int height;
	int lenght;
	
	void calArea() {
		area = (height*lenght)/2;
	}
	
	public static void main (String [] args) {
		Triangle[] ta = new Triangle[4];
		int x = 0;
		while (x<4) {
			ta[x] = new Triangle();
			ta[x].height = (x+1)*2;
			ta[x].lenght = x+4;
			ta[x].calArea();
			System.out.print("Triangle " + x + " area");
			System.out.println("=" + ta[x].area);
			x=x+1;
		}
	}
}