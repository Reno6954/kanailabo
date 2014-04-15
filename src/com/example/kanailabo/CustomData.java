package com.example.kanailabo;

public class CustomData{
	public static final int
			LABO	= R.id.labo,
			CAMPUS	= R.id.campus,
			HOME	= R.id.home;
	private String grade;
	private String name;
	private int status;
	
	public CustomData() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public CustomData(String grade, String name, int status) {
		super();
		this.grade = grade;
		this.name = name;
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
