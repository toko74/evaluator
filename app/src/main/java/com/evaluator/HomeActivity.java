package com.evaluator;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.evaluator.group.GroupDetailsFragment;



public class HomeActivity extends AppCompatActivity{



	private Toolbar toolbar;



	private DrawerLayout dlDrawer;



	private ActionBarDrawerToggle drawerToggle;



	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerToggle = setupDrawerToggle();
		dlDrawer.setDrawerListener(drawerToggle);

		NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem){

				menuItemSelected(menuItem);
				return true;
			}
		});

		if(savedInstanceState == null){
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.add(R.id.container, GroupDetailsFragment.newInstance());
			ft.commit();
			setTitle(getString(R.string.group_details_title));
		}
	}



	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}



	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}



	private ActionBarDrawerToggle setupDrawerToggle() {
		return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
	}



	private void menuItemSelected(MenuItem menuItem){
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



}
