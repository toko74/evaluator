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
public class TriangleFragment extends Fragment{


	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.triangle_fragment,container,false);



		return view;
	}



	public static TriangleFragment newInstance(){
		TriangleFragment fragment = new TriangleFragment();


		return fragment;
	}
}



