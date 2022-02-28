package servlet;

public class user {

	/*
	private int id;
	private String name;
	private int resultA;
	private int resultB;
	private int resultC;
	*/
	
	int id;
	String name;
	int resultA;
	int resultB;
	int resultC;
	String employment;

	public user(int id, String name, int resultA, int resultB, int resultC,String employment) {

		this.id = id;
		this.name = name;
		this.resultA = resultA;
		this.resultB = resultB;
		this.resultC = resultC;
		this.employment=employment;

	}

	public int[] getUser() {

		int[] user = { this.id, this.resultA, this.resultB, this.resultC };
		return user;

	}

	public String getName() {

		return this.name;
		
	}
}
