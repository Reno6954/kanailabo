package com.example.kanailabo;

import java.util.List;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<CustomData>{
	private LayoutInflater layoutInflater;

	private TextView gradeText;
	private TextView nameText;
	private LinearLayout custom_linear;

	public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
		super(context, textViewResourceId, objects);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 特定の行(position)のデータを得る
		final CustomData item = (CustomData)getItem(position);
		final String id = Integer.toString(position);
		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.customdata, null);
		}
		setLayout(position,convertView);
		// CustomDataのデータをViewの各Widgetにセットする

		gradeText.setText(item.getGrade());
		nameText.setText(item.getName());
		custom_linear.setBackgroundColor(item.getStatus());

		// ラジオグループのチェック状態が変更された時に呼び出されるコールバックリスナーを登録します
		/*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				// ラジオグループのチェック状態が変更された時に呼び出されます
				// チェック状態が変更されたラジオボタンのIDが渡されます
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String Status = "";
				item.setStatus(checkedId);
				switch (checkedId) {
				case CustomData.LABO:
					Status = ZERO;
					break;
				case CustomData.CAMPUS:
					Status = ONE;
					break;
				case CustomData.HOME:
					Status = TWO;
					break;
				}
				ButtonPost CORR = new ButtonPost(CORR_URL);
				CORR.execute(ID,id,id,Status);
			}
		});
		radioGroup.check(item.getStatus());*/
		return convertView;
	}
	/**
	 * set find view by id
	 */
	private void setLayout(int position,View convertView){
		gradeText = (TextView)convertView.findViewById(R.id.grade);
		nameText = (TextView)convertView.findViewById(R.id.name);
		custom_linear = (LinearLayout)convertView.findViewById(R.id.custom_linear);
	}
}
