package com.evaluator.group;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


	private EditText nameEditText;


	private EditText fNameEditText;



	private CandidateStatus status;



	public AddCandidateDialog(){}




	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){


		View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_person_dialog,null);
		setupFields(view);


		AlertDialog dialog =  new AlertDialog.Builder(getActivity())
				.setTitle(R.string.person_details)
				.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								handleKeyBord(false);
								Intent intent = getActivity().getIntent();

								Candidate candidate = new Candidate();
								candidate.setId(Integer.parseInt(userId.getText().toString()));
								candidate.setName(nameEditText.getText().toString().isEmpty() ? "John" : nameEditText.getText().toString());
								candidate.setfName(fNameEditText.getText().toString().isEmpty() ? "Doe" : fNameEditText.getText().toString());
								candidate.setCandidateStatus(status != null ? status : CandidateStatus.STARTED);

								intent.putExtra("candidate", candidate);
								getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

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

	private void setupFields(View view){

		userId = (EditText)view.findViewById(R.id.userId);
		nameEditText = (EditText)view.findViewById(R.id.candidateName);
		fNameEditText = (EditText)view.findViewById(R.id.candidateFamilyName);

		if(getArguments() == null) return;

		Candidate candidate = getArguments().getParcelable("candidate");
		if(candidate == null) return;

		status = candidate.getCandidateStatus();

		userId.setText(String.valueOf(candidate.getId()));
		nameEditText.setText(candidate.getName());
		fNameEditText.setText(candidate.getfName());
	}



	public static AddCandidateDialog newInstance(){
		AddCandidateDialog fragment = new AddCandidateDialog();

		return fragment;
	}



	public static AddCandidateDialog newInstance(Candidate candidate){
		AddCandidateDialog fragment = new AddCandidateDialog();

		if(candidate != null){
			Bundle bundle = new Bundle();
			bundle.putParcelable("candidate", candidate);
			fragment.setArguments(bundle);
		}

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
