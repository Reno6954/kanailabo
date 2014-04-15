package com.example.kanailabo;

import android.content.Context;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomData>{
	private LayoutInflater layoutInflater_;
	 
	 public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
	 super(context, textViewResourceId, objects);
	 layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	 // 特定の行(position)のデータを得る
	 final CustomData item = (CustomData)getItem(position);
	 
	 // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
	 if (null == convertView) {
	 convertView = layoutInflater_.inflate(R.layout.customdata, null);
	 }
	 
	 // CustomDataのデータをViewの各Widgetにセットする
	 
	 TextView gradeText;
	 gradeText = (TextView)convertView.findViewById(R.id.grade);
	 gradeText.setText(item.getGrade());
	 
	 TextView nameText;
	 nameText = (TextView)convertView.findViewById(R.id.name);
	 nameText.setText(item.getName());
	 
	 RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.status);
     // 指定した ID のラジオボタンをチェックします
     // チェックされているラジオボタンの ID を取得します
     RadioButton radioLabo = (RadioButton) convertView.findViewById(R.id.labo);
     RadioButton radioCampus = (RadioButton) convertView.findViewById(R.id.campus);
     RadioButton radioHome = (RadioButton) convertView.findViewById(R.id.home);
     // ラジオグループのチェック状態が変更された時に呼び出されるコールバックリスナーを登録します
     radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
         // ラジオグループのチェック状態が変更された時に呼び出されます
         // チェック状態が変更されたラジオボタンのIDが渡されます
         public void onCheckedChanged(RadioGroup group, int checkedId) { 
             item.setStatus(checkedId);
         }
     });
	 radioGroup.check(item.getStatus());
	 return convertView;
	 }
}
