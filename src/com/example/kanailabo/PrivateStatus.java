package com.example.kanailabo;

import android.app.Activity;
import android.graphics.Color;
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
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
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
		
		if(status == CustomData.LABO)
			linear.setBackgroundColor(Color.BLUE);
		else if(status == CustomData.CAMPUS)
			linear.setBackgroundColor(Color.YELLOW);
		else if(status == CustomData.HOME)
			linear.setBackgroundColor(Color.RED);
		Name.setText(name);
		
		Button button1 = (Button)findViewById(R.id.lab);
		button1.setOnClickListener(this);
		Button button2 = (Button)findViewById(R.id.campus);
		button2.setOnClickListener(this);
		Button button3 = (Button)findViewById(R.id.home);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		final String id = Integer.toString(position);
		//Toast.makeText(this,, Toast.LENGTH_LONG).show();
		String Status = "";
		switch (v.getId()) {
			case R.id.lab:
				Status = ZERO;
				linear.setBackgroundColor(Color.BLUE);
				break;
			case R.id.campus:
				Status = ONE;
				linear.setBackgroundColor(Color.YELLOW);
				break;
			case R.id.home:
				Status = TWO;
				linear.setBackgroundColor(Color.RED);
				break;
		}
		ButtonPost CORR = new ButtonPost(CORR_URL);
		CORR.execute(ID,id,id,Status);
	}
	
	
	
	
}
