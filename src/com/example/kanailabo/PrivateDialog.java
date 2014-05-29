package com.example.kanailabo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class PrivateDialog extends Dialog implements OnClickListener{
	Bitmap image;
	String name;
	int status;
	int position;
	
	int st = 3;
	private static final String CORR_URL = "http://kanai-lab.herokuapp.com/corr.php";
	private static final String ID = "id";
	private static final String LAB = "0";
	private static final String CAM = "1";
	private static final String HOM = "2";
 
    public PrivateDialog(Context context, Bitmap image, String name, int status,int position) {
		super(context);
		this.image = image;
		this.name = name;
		this.status = status;
		this.position = position;
	}

	public PrivateDialog(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
		// (これしないとグレーのタイトルが付く)
		//super.onCreateの後で呼ぶと実行時エラー
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        // dialog.xml を利用する
        setContentView(R.layout.privatedialog);
        
        Log.e("name", this.name);
        Log.e("status", String.valueOf(this.status));
        Log.e("position", String.valueOf(this.position));
        
        setObject();
        
        // タイトルなし
        
        // 画面の大きさに合わせる
        // (これしないと場合によっては極小表示になる)
        LayoutParams lp = getWindow().getAttributes();
        lp.width = LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        
        Button button1 = (Button)findViewById(R.id.lab);
		button1.setOnClickListener(this);
		Button button2 = (Button)findViewById(R.id.campus);
		button2.setOnClickListener(this);
		Button button3 = (Button)findViewById(R.id.home);
		button3.setOnClickListener(this);
		Button button4 = (Button)findViewById(R.id.Contact);
		button4.setOnClickListener(this);
        
		/*
        // 「OK」ボタン
        findViewById(R.id.lab).setOnClickListener(
                new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                dismiss();
            }
 
        });
        // 「Cancel」ボタン
        findViewById(R.id.campus).setOnClickListener(
            new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                cancel();
            }
 
        });
     // 「Cancel」ボタン
        findViewById(R.id.home).setOnClickListener(
            new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                cancel();
            }
 
        });
     // 「Cancel」ボタン
        findViewById(R.id.Contact).setOnClickListener(
            new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                cancel();
            }
 
        });
        */
    }
	
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		// TODO 自動生成されたメソッド・スタブ
		final String id = Integer.toString(position);
		//Toast.makeText(this,, Toast.LENGTH_LONG).show();
		String NewStatus = "";
		switch (v.getId()) {
			case R.id.lab:
				NewStatus = LAB;
				st = 0;
				setResult();
				break;
			case R.id.campus:
				NewStatus = CAM;
				st = 1;
				setResult();
				break;
			case R.id.home:
				NewStatus = HOM;
				st = 2;
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
	
	private void setResult(){
		//data = new Intent();
		//data.putExtra("status", st);
		//setResult(RESULT_OK,data);
		Log.v("st",String.valueOf(st));
		dismiss();
	}
	
	private void setObject(){
		Bitmap reBitmap= Bitmap.createScaledBitmap(this.image, 200, 200, false);
        ImageView imageView1 = (ImageView)this.findViewById(R.id.imageView1);
        imageView1.setImageBitmap(reBitmap);
        TextView re_name =(TextView)this.findViewById(R.id.name);
        re_name.setText(this.name);
	}
}