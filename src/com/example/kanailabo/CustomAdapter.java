package com.example.kanailabo;

import java.util.List;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<CustomData>{
	private LayoutInflater layoutInflater;

	private TextView gradeText;
	private TextView nameText;
	private LinearLayout custom_linear;
	private ImageView imageView1;
	WindowManager wm;
	private static Context context;
	Bitmap image = null;
	Integer[] switch_images = new Integer[100];

	public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
		super(context, textViewResourceId, objects);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		CustomAdapter.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//画像配列
		/*if(switch_images == null){
			for(int i = 0;i < switch_images.length;i++){
				switch_images[i] = R.drawable.image + i;
				Log.e("imageNo", String.valueOf(switch_images[i]));
			}
		}
		*/
		// 特定の行(position)のデータを得る
		final CustomData item = (CustomData)getItem(position);
		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.customdata, null);
			// ウィンドウマネージャのインスタンス取得
			wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
			// ディスプレイのインスタンス生成
			Display disp = wm.getDefaultDisplay();
			Point size = new Point();
			disp.getSize(size);
			//widthの幅がわかったので、ここでは5列の例
			convertView.setMinimumWidth(size.x/6);
			convertView.setMinimumHeight(size.y/5);
		}
		//画像
		
		
		setLayout(position,convertView);
		// CustomDataのデータをViewの各Widgetにセットする
		//image = BitmapFactory.decodeResource(context.getResources(), R.drawable.image);
		gradeText.setText(item.getGrade());
		nameText.setText(item.getName());
		custom_linear.setBackgroundColor(item.getStatus());
		imageView1.setImageBitmap(circle(position));
		
		return convertView;
		
	}
	/**
	 * set find view by id
	 */
	private void setLayout(int position,View convertView){
		gradeText = (TextView)convertView.findViewById(R.id.grade);
		nameText = (TextView)convertView.findViewById(R.id.name);
		custom_linear = (LinearLayout)convertView.findViewById(R.id.custom_linear);
		imageView1 = (ImageView)convertView.findViewById(R.id.imageview1);
	}
	
	public Bitmap circle(int p){
		// リソースからbitmapを作成
		//画像ID指定
		Bitmap bitmap;
		if(p < 6 && 0 < p){
			//String ID = "R.drawable.image" + p;
			//Log.e("ID", ID);
			int viewId = context.getResources().getIdentifier("image"+p, "drawable", context.getPackageName());
			Log.e("viewId", String.valueOf(viewId));
			bitmap = BitmapFactory.decodeResource(context.getResources(), viewId);
		}
		else{
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image);
			Log.e("NoID", "R.drawable.image");
		}
		//画像リサイズ
		Bitmap reBitmap= Bitmap.createScaledBitmap(bitmap, 80, 80, false);
		
		Bitmap result = Bitmap.createBitmap(reBitmap.getWidth(), reBitmap.getHeight(), Bitmap.Config.ARGB_8888);
			 
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, reBitmap.getWidth(), reBitmap.getHeight());
			 
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.BLUE);
			 
		canvas.drawCircle(reBitmap.getWidth() / 2, reBitmap.getHeight() / 2, reBitmap.getHeight() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(reBitmap, rect, rect, paint);
		
		return result;
	}
}
