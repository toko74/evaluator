package com.evaluator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * @author Gilad Opher
 */
public class SprintFragment extends Fragment{


	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		((HomeActivity)activity).onSectionAttached(R.string.sprint_title);
	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.sprint_fragment,container,false);



		return view;
	}



	public static SprintFragment newInstance(){
		SprintFragment fragment = new SprintFragment();


		return fragment;
	}
}