package com.evaluator.group;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.evaluator.R;



/**
 * @author Gilad Opher
 */
public class AddCandidateDialog extends DialogFragment{



	private EditText userId;




	public AddCandidateDialog(){}




	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){


		View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_person_dialog,null);

		userId = (EditText)view.findViewById(R.id.userId);

		AlertDialog dialog =  new AlertDialog.Builder(getActivity())
				.setTitle(R.string.person_details)
				.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								handleKeyBord(false);
									String sId = userId.getText().toString();
								getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent().putExtra("id",sId));

							}
						}
				)
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								handleKeyBord(false);
							}
						}
				)
				.setView(view)
				.create();

		handleKeyBord(true);
		return dialog;
	}



	public static AddCandidateDialog newInstance(){
		AddCandidateDialog fragment = new AddCandidateDialog();

		return fragment;
	}



	private void handleKeyBord(boolean show){
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm == null) return;

		if(show){
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}else{
			imm.hideSoftInputFromWindow(userId.getWindowToken(), 0);
		}
	}

}
