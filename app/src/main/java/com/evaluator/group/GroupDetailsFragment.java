package com.evaluator.group;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.*;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.evaluator.HomeActivity;
import com.evaluator.R;
import com.evaluator.db.EvaluatorAppDB;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Gilad Opher
 */
public class GroupDetailsFragment extends Fragment{



	private static final int NEW_CANDIDATE_REQUEST_CODE = 9090;



	private FloatingActionButton addPerson;



	private RecyclerView candidateRecyclerView;



	private CandidateAdapter candidateAdapter;



	private RecyclerView.LayoutManager candidateLayoutManager;



	private List<Candidate> candidateList;


	private EvaluatorAppDB db;


	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		((HomeActivity)activity).onSectionAttached(R.string.group_details_title);
	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.group_details_fragment,container,false);

		addPerson = (FloatingActionButton)view.findViewById(R.id.add_person);

		addPerson.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				openAddPersonDialog();
			}
		});


		db = new EvaluatorAppDB(getActivity());

		candidateList = db.getCandidates();

		candidateRecyclerView = (RecyclerView)view.findViewById(R.id.candidatesList);


		candidateRecyclerView.setHasFixedSize(false);

		// use a linear layout manager
		candidateLayoutManager = new LinearLayoutManager(getActivity());
		candidateRecyclerView.setLayoutManager(candidateLayoutManager);

		// specify an adapter (see also next example)
		candidateAdapter = new CandidateAdapter(candidateList);
		candidateRecyclerView.setAdapter(candidateAdapter);

		addPerson.attachToRecyclerView(candidateRecyclerView);

		return view;
	}





	private void openAddPersonDialog(){
		openAddPersonDialog(null);
	}



	private void openAddPersonDialog(Candidate candidate){
		AddCandidateDialog dialog = AddCandidateDialog.newInstance(candidate);
		dialog.setTargetFragment(this,NEW_CANDIDATE_REQUEST_CODE);
		dialog.show(getFragmentManager(),"dialog");
	}



	public static GroupDetailsFragment newInstance(){
		GroupDetailsFragment fragment = new GroupDetailsFragment();

		return fragment;
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){

		Candidate candidate = data.getParcelableExtra("candidate");
		if(candidate == null) return;

		db.insert(candidate);
		candidateAdapter.addItem(candidate);
	}



	private class CandidateListener implements PopupMenu.OnMenuItemClickListener{


		private Candidate candidate;


		private CandidateListener(Candidate candidate){
			this.candidate = candidate;
		}


		@Override
		public boolean onMenuItemClick(MenuItem item){


			switch (item.getItemId()){
				case R.id.delete_candidate:
					candidateAdapter.removeCandidate(candidate);
					db.deleteCandidate(candidate);
					break;

				case R.id.edit_candidate:
					openAddPersonDialog(candidate);
					break;

				case R.id.moov_up_candidate:
					candidateAdapter.moovUp(candidate);
					break;

				case R.id.moov_down_candidate:
					candidateAdapter.moovDown(candidate);
					break;

			}



			return false;
		}
	}




	public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {



		public int test;



		private List<Candidate> candidates;



		// Provide a reference to the views for each data item
		// Complex data items may need more than one view per item, and
		// you provide access to all the views for a data item in a view holder
		public class ViewHolder extends RecyclerView.ViewHolder {
			// each data item is just a string in this case


			public TextView id;
			public TextView name;
			public TextView status;
			private ImageView menu;


			public ViewHolder(View v) {
				super(v);
				id = (TextView)v.findViewById(R.id.candidateId);
				name = (TextView)v.findViewById(R.id.candidateName);
				status = (TextView)v.findViewById(R.id.candidateStatus);
				menu = (ImageView)v.findViewById(R.id.candidateMenu);
			}
		}





		// Provide a suitable constructor (depends on the kind of dataset)
		public CandidateAdapter(List<Candidate> candidates) {
			this.candidates = candidates;
		}




		// Create new views (invoked by the layout manager)
		@Override
		public CandidateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			// create a new view
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_row_layout, parent, false);

			ViewHolder vh = new ViewHolder(v);
			return vh;
		}



		// Replace the contents of a view (invoked by the layout manager)
		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			// - get element from your dataset at this position
			// - replace the contents of the view with that element
			final Candidate candidate = candidates.get(position);

			holder.id.setText(candidate.getId()+"");
			holder.name.setText(candidate.getName() + " " + candidate.getfName());
			holder.status.setText(candidate.getCandidateStatus().getStatusRes());
			holder.menu.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					PopupMenu popup = new PopupMenu(getActivity(), v);
					MenuInflater inflater = popup.getMenuInflater();
					inflater.inflate(R.menu.candidate_menu_options, popup.getMenu());
					popup.setOnMenuItemClickListener(new CandidateListener(candidate));
					popup.show();
				}
			});
		}



		// Return the size of your dataset (invoked by the layout manager)
		@Override
		public int getItemCount() {
			return candidates.size();
		}


		public void addItem(Candidate candidate) {

			candidates.add(candidate);
			notifyItemInserted(candidates.size()-1);
		}


		public void removeCandidate(Candidate candidate){
			int index = candidates.indexOf(candidate);
			candidates.remove(candidate);
			notifyItemRemoved(index);
		}


		public void moovUp(Candidate candidate){
			int index = candidates.indexOf(candidate);
			if(index > 0){
				candidates.remove(candidate);
				candidates.add(index-1, candidate);
				notifyItemMoved(index, (index - 1));
			}
		}

		public void moovDown(Candidate candidate){
			int index = candidates.indexOf(candidate);
			if(index < candidates.size()-1){
				candidates.remove(candidate);
				candidates.add(index+1, candidate);
				notifyItemMoved(index, (index + 1));
			}
		}

	}
}
