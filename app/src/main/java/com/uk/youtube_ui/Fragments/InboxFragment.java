package com.uk.youtube_ui.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uk.youtube_ui.Activities.MainActivity;
import com.uk.youtube_ui.Adapters.CustomPagerAdapter;
import com.uk.youtube_ui.R;

import java.util.ArrayList;

public class InboxFragment extends Fragment {
	
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private CustomPagerAdapter pagerAdapter;
	
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.inbox_layout, null);
	}
	
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tabLayout = view.findViewById(R.id.tabLayout);
		viewPager = view.findViewById(R.id.viewPager);
		
		
		ImageView imgTheme = view.findViewById(R.id.imgThemeIcon);
		
		if (MainActivity.nightMode) {
			imgTheme.setBackground(getResources().getDrawable(R.drawable.ic_day));
			imgTheme.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
		} else {
			imgTheme.setBackground(getResources().getDrawable(R.drawable.ic_night));
			imgTheme.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#606060")));
		}
		
		
		imgTheme.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Toggle mode
				MainActivity.nightMode = !MainActivity.nightMode;
				
				//Updating setting
				if (MainActivity.nightMode) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
				}
				
				getActivity().finish();
				getContext().startActivity(new Intent(getContext(), getActivity().getClass()));
				
			}
		});
		
		
		ArrayList<Fragment> fragments = new ArrayList<>();
		fragments.add(new MessagesFragment());
		fragments.add(new NotificationsFragment());
		
		ArrayList<String> tabNames = new ArrayList<>();
		tabNames.add("MESSAGES");
		tabNames.add("NOTIFICATIONS");
		
		pagerAdapter = new CustomPagerAdapter(getChildFragmentManager(), fragments, tabNames, fragments.size());
		viewPager.setAdapter(pagerAdapter);
		
		tabLayout.setupWithViewPager(viewPager);
		
	}
	
	
}
