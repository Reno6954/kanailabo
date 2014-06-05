package com.example.kanailabo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class PrivateDialog extends Dialog implements OnClickListener{
	Bitmap image;
	String name;
	int status;
	int position;
	AdapterView<?> parent;
	CustomAdapter customAdapter;
	Context context;
	
	int st = 3;
	private static final String CORR_URL = "http://kanai-lab.herokuapp.com/corr.php";
	private static final String ID = "id";
	private static final String LAB = "0";
	private static final String CAM = "1";
	private static final String HOM = "2";
	
	CustomData item;
	private int color1 = 0;
	private int color2 = 0;
	private int color3 = 0;
	
	CustomProgress progressDialog;
 
    public PrivateDialog(Context context, Bitmap image, String name, int status,int position,AdapterView<?> parent,CustomAdapter customAdapter) {
		super(context);
		this.context = context;
		this.image = image;
		this.name = name;
		this.status = status;
		this.position = position;
		this.parent = parent;
		this.customAdapter = customAdapter;
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
        
        //dialogの外枠を消す
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        color1 = context.getResources().getColor(R.color.color1);
		color2 = context.getResources().getColor(R.color.color2);
		color3 = context.getResources().getColor(R.color.color3);
        
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
		//Button button4 = (Button)findViewById(R.id.Contact);
		//button4.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		// TODO 自動生成されたメソッド・スタブ
		final String id = Integer.toString(position);

		//ダイアログ
		//progressDialog = new CustomProgress(context);
		//progressDialog.show();
		
		dismiss();
		// クリックされたアイテムを取得する。
		item = (CustomData) parent.getItemAtPosition(position);
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
		}
		if (NewStatus != ""){
			ButtonPost CORR = new ButtonPost(CORR_URL);
			CORR.execute(ID,id,id,NewStatus);
		}
		//dismiss();
		//progressDialog.dismiss();
	}
	
	private void setResult(){
		if (st == 0)
			item.setStatus(color1);
		else if (st == 1)
			item.setStatus(color2);	
		else if (st == 2)
			item.setStatus(color3);
		this.customAdapter.notifyDataSetChanged();
	}
	
	private void setObject(){
		Bitmap reBitmap= Bitmap.createScaledBitmap(this.image, 200, 200, false);
        ImageView imageView1 = (ImageView)this.findViewById(R.id.imageView1);
        imageView1.setImageBitmap(reBitmap);
        TextView re_name =(TextView)this.findViewById(R.id.name);
        re_name.setText(this.name);
	}
}