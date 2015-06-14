package com.evaluator.exercises;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.evaluator.R;
import com.evaluator.db.EvaluatorAppDB;
import com.evaluator.group.Candidate;

import java.util.List;
import java.util.concurrent.TimeUnit;



/**
 * @author Gilad Opher
 */
public class SprintActivity extends AppCompatActivity{



	private static final int TOTAL_TIME = 30;



	private CountDownTimer countDownTimer;



	private TextView timerTextView;



	private GridView candidatesGridView;





	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sprint_exercise_layout);

		timerTextView = (TextView)findViewById(R.id.timer);
		candidatesGridView = (GridView)findViewById(R.id.candidatesGrid);


		timerTextView.setText(String.format("%02d:%02d", TOTAL_TIME, 0));

		findViewById(R.id.start).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startTimer(TOTAL_TIME);
			}
		});

		findViewById(R.id.pause).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				stopTimer();
			}
		});



		EvaluatorAppDB db = new EvaluatorAppDB(this);
		List<Candidate> candidateList = db.getCandidates();
		candidatesGridView.setAdapter(new CandidatesGridAdapter(this, candidateList));

	}



	private void startTimer(int countDownTime){
		countDownTimer = new CountDownTimer(TimeUnit.MINUTES.toMillis((long)countDownTime), 1000){
			@Override
			public void onTick(long millisUntilFinished){
				int seconds = (int)TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;
				int minutes = (int)TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);

				timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
			}

			@Override
			public void onFinish(){

				timerTextView.setText("00:00");
			}
		}.start();
	}



	private void stopTimer(){

		if(countDownTimer != null){
			countDownTimer.cancel();
			timerTextView.setText(String.format("%02d:%02d", TOTAL_TIME, 0));
		}
	}




	private class CandidatesGridAdapter extends BaseAdapter{



		private Context context;



		private List<Candidate> candidateList;



		public CandidatesGridAdapter(Context context, List<Candidate> candidateList){
			this.context = context;
			this.candidateList = candidateList;
		}


		@Override
		public int getCount(){
			return candidateList.size();
		}

		@Override
		public Object getItem(int position){
			return candidateList.get(position);
		}

		@Override
		public long getItemId(int position){
			return candidateList.get(position).getId();
		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			TextView item;
			if (convertView == null){
				item = new TextView(context);
				item.setBackgroundResource(R.drawable.candidate_ex_button);
				int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics() );
				item.setLayoutParams(new GridView.LayoutParams(px, px));
				item.setGravity(Gravity.CENTER);
				item.setPadding(10,10,10,10);
				item.setTextAppearance(context, R.style.candidate_ex_button);
			} else
				item = (TextView)convertView;

			item.setText(""+candidateList.get(position).getId());

			return item;
		}
	}


}
