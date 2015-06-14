package com.evaluator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evaluator.exercises.SprintActivity;



/**
 * @author Gilad Opher
 */
public class SprintFragment extends Fragment{



	private FloatingActionButton startSprint;



	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.sprint_fragment,container,false);


		startSprint = (FloatingActionButton)view.findViewById(R.id.start_sprint);
		startSprint.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getActivity(), SprintActivity.class));
			}
		});

		return view;
	}



	public static SprintFragment newInstance(){
		SprintFragment fragment = new SprintFragment();


		return fragment;
	}
}
