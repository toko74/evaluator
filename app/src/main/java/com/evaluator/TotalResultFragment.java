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
public class TotalResultFragment extends Fragment{



	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.total_results_fragment,container,false);



		return view;
	}




	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		getActivity().setTitle(R.string.total_result_title);
	}



	public static TotalResultFragment newInstance(){
		TotalResultFragment fragment = new TotalResultFragment();


		return fragment;
	}
}
