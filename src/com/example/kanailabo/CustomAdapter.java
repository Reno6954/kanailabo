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
	private LayoutInflater layoutInflater;

	private TextView gradeText;
	private TextView nameText;
	private RadioGroup radioGroup;  
	private RadioButton radioLabo;  
	private RadioButton radioCampus; 
	private RadioButton radioHome; 

	public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
		super(context, textViewResourceId, objects);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 特定の行(position)のデータを得る
		final CustomData item = (CustomData)getItem(position);

		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.customdata, null);
		}
		setLayout(convertView);
		// CustomDataのデータをViewの各Widgetにセットする

		gradeText.setText(item.getGrade());
		nameText.setText(item.getName());

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
	/**
	 * set find view by id
	 */
	private void setLayout(LayoutInflater convertView){
		gradeText = (TextView)convertView.findViewById(R.id.grade);
		nameText = (TextView)convertView.findViewById(R.id.name);
		radioGroup = (RadioGroup)convertView.findViewById(R.id.status);
		radioLabo = (RadioButton)convertView.findViewById(R.id.labo);     
		radioCampus = (RadioButton)convertView.findViewById(R.id.campus);	 
		radioHome = (RadioButton)convertView.findViewById(R.id.home);
	}
}
