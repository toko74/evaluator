package com.evaluator;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.*;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.evaluator.group.GroupDetailsFragment;



public class HomeActivity extends AppCompatActivity{



	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;



	private Toolbar toolbar;



	private DrawerLayout dlDrawer;



	private ActionBarDrawerToggle drawerToggle;



	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);

//		mNavigationDrawerFragment = (NavigationDrawerFragment)
//				getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerToggle = setupDrawerToggle();
		dlDrawer.setDrawerListener(drawerToggle);


//		final ActionBar ab = getSupportActionBar();
//		ab.setDisplayHomeAsUpEnabled(true);


		NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);


		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem){

				menuItemSelected(menuItem);
				return true;
			}
		});

//
//		// Set up the drawer.
//		mNavigationDrawerFragment.setUp(
//				R.id.navigation_drawer,
//				(DrawerLayout)findViewById(R.id.drawer_layout));

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.container, GroupDetailsFragment.newInstance());
		ft.commit();

		setTitle(getString(R.string.group_details_title));
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.

		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		drawerToggle.onConfigurationChanged(newConfig);
	}


	private ActionBarDrawerToggle setupDrawerToggle() {
		return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
	}




	private void menuItemSelected(MenuItem menuItem){
		// update the main content by replacing fragments
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		String mTitle = "";
		switch (menuItem.getItemId()){
			case R.id.navigation_item_1:
				ft.replace(R.id.container, GroupDetailsFragment.newInstance());
				mTitle = getString(R.string.group_details_title);
				break;

			case R.id.navigation_item_2:
				ft.replace(R.id.container, SprintFragment.newInstance());
				mTitle = getString(R.string.sprint_title);
				break;

			case R.id.navigation_item_3:
				ft.replace(R.id.container, SocialStretcherFragment.newInstance());
				mTitle = getString(R.string.socila_stretcher_title);
				break;

			case R.id.navigation_item_4:
				ft.replace(R.id.container, TriangleFragment.newInstance());
				mTitle = getString(R.string.triangle_title);
				break;

			case R.id.navigation_item_5:
				ft.replace(R.id.container, DiggingFragment.newInstance());
				mTitle = getString(R.string.digging_title);
				break;

			case R.id.navigation_item_6:
				ft.replace(R.id.container, TotalResultFragment.newInstance());
				mTitle = getString(R.string.total_result_title);
				break;
		}

		ft.commit();

		menuItem.setChecked(true);
		setTitle(mTitle);
		dlDrawer.closeDrawers();

	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	//	public void onSectionAttached(int number){
//		switch (number){
//			case 1:
//				mTitle = getString(R.string.title_section1);
//				break;
//			case 2:
//				mTitle = getString(R.string.title_section2);
//				break;
//			case 3:
//				mTitle = getString(R.string.title_section3);
//				break;
//		}
//	}
//
//	public void restoreActionBar(){
//		ActionBar actionBar = getSupportActionBar();
//		actionBar.setDisplayShowTitleEnabled(true);
//		actionBar.setTitle(mTitle);
//	}




}
