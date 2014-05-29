package com.example.kanailabo;

import android.graphics.Bitmap;

public class CustomData{
	private String grade;
	private String name;
	private int status;
	private Bitmap bitmap;
	
	public CustomData() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public CustomData(String grade, String name, int status, Bitmap bitmap) {
		super();
		this.grade = grade;
		this.name = name;
		this.status = status;
		this.bitmap = bitmap;
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
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
}
