package com.example.kanailabo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrivateStatus extends Activity implements OnClickListener{
	String name = "";
	int status = 0;
	int position = 0;
	int st = 3;
	TextView Name;
	Bundle bundle;
	Intent data;
	
	private static final String CORR_URL = "http://kanai-lab.herokuapp.com/corr.php";
	private static final String ID = "id";
	private static final String LAB = "0";
	private static final String CAM = "1";
	private static final String HOM = "2";
	private LinearLayout linear;
	private int color4 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.private_status);
		linear = (LinearLayout)findViewById(R.id.linear);
		Bundle list;
		list = getIntent().getExtras();
		Name = (TextView)findViewById(R.id.name);
		color4 = getResources().getColor(R.color.color4);
		
		if(list != null){
			name = list.getString("name");
			status = list.getInt("status");
			position = list.getInt("position");
		}
		linear.setBackgroundColor(color4);
		Name.setText(name);
		
		Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
		
		Button button1 = (Button)findViewById(R.id.lab);
		button1.setOnClickListener(this);
		button1.setWidth(p.x/3);
		button1.setHeight(p.y/4);
		Button button2 = (Button)findViewById(R.id.campus);
		button2.setOnClickListener(this);
		button2.setWidth(p.x/3);
		button2.setHeight(p.y/4);
		Button button3 = (Button)findViewById(R.id.home);
		button3.setOnClickListener(this);
		button3.setWidth(p.x/3);
		button3.setHeight(p.y/4);
		Button button4 = (Button)findViewById(R.id.back);
		button4.setOnClickListener(this);
		button4.setWidth(p.x/3);
		button4.setHeight(p.y/4);
		Button button5 = (Button)findViewById(R.id.Contact);
		button5.setOnClickListener(this);
		button5.setWidth(p.x/3);
		button5.setHeight(p.y/4);
		ImageView imageView = (ImageView)findViewById(R.id.imageview1);
		//imageView.setImageBitmap(CustomAdapter.circle(position));
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		final String id = Integer.toString(position);
		//Toast.makeText(this,, Toast.LENGTH_LONG).show();
		String NewStatus = "";
		switch (v.getId()) {
			case R.id.lab:
				NewStatus = LAB;
				st = 0;
				//linear.setBackgroundColor(getResources().getColor(R.color.color1));
				setResult();
				break;
			case R.id.campus:
				NewStatus = CAM;
				st = 1;
				//linear.setBackgroundColor(getResources().getColor(R.color.color2));
				setResult();
				break;
			case R.id.home:
				NewStatus = HOM;
				st = 2;
				//linear.setBackgroundColor(getResources().getColor(R.color.color3));
				setResult();
				break;
			case R.id.back:
				setResult();
				break;
		}
		if (NewStatus != ""){
			ButtonPost CORR = new ButtonPost(CORR_URL);
			CORR.execute(ID,id,id,NewStatus);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO 自動生成されたメソッド・スタブ
		setResult();
		super.onBackPressed();
		Log.v("private status","call onBackPressed");
	}
	private void setResult(){
		data = new Intent();
		data.putExtra("status", st);
		setResult(RESULT_OK,data);
		Log.v("st",String.valueOf(st));
		finish();
	}
}
