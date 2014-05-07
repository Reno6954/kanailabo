package com.example.kanailabo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrivateStatus extends Activity implements OnClickListener{
	String name = "";
	int status = 0;
	int position = 0;
	TextView Name;
	
	private static final String CORR_URL = "http://kanai-lab.herokuapp.com/corr.php";
	private static final String ID = "id";
	private static final String LAB = "0";
	private static final String CAM = "1";
	private static final String HOM = "2";
	private LinearLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.private_status);
		linear = (LinearLayout)findViewById(R.id.linear);
		Bundle list;
		list = getIntent().getExtras();
		Name = (TextView)findViewById(R.id.name);
		
		if(list != null){
			name = list.getString("name");
			status = list.getInt("status");
			position = list.getInt("position");
		}
		linear.setBackgroundColor(status);
		Name.setText(name);
		
		Button button1 = (Button)findViewById(R.id.lab);
		button1.setOnClickListener(this);
		Button button2 = (Button)findViewById(R.id.campus);
		button2.setOnClickListener(this);
		Button button3 = (Button)findViewById(R.id.home);
		button3.setOnClickListener(this);
		Button button4 = (Button)findViewById(R.id.back);
		button4.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
		finish();
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
				linear.setBackgroundColor(getResources().getColor(R.color.color1));
				break;
			case R.id.campus:
				NewStatus = CAM;
				linear.setBackgroundColor(getResources().getColor(R.color.color2));
				break;
			case R.id.home:
				NewStatus = HOM;
				linear.setBackgroundColor(getResources().getColor(R.color.color3));
				break;
			case R.id.back:
				Intent intent = new Intent(this,MainActivity.class);
				startActivity(intent);
				finish();
				break;
		}
		if (NewStatus != ""){
			ButtonPost CORR = new ButtonPost(CORR_URL);
			CORR.execute(ID,id,id,NewStatus);
		}
	}
}
