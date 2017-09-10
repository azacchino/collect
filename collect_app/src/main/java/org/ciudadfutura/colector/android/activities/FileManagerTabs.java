/*
 * Copyright (C) 2017 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ciudadfutura.colector.android.activities;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.ciudadfutura.colector.android.R;
import org.ciudadfutura.colector.android.adapters.ViewPagerAdapter;
import org.ciudadfutura.colector.android.application.Collect;
import org.ciudadfutura.colector.android.fragments.DataManagerList;
import org.ciudadfutura.colector.android.fragments.FormManagerList;
import org.ciudadfutura.colector.android.views.SlidingTabLayout;

import java.util.ArrayList;

public class FileManagerTabs extends AppCompatActivity {

    private DataManagerList dataManagerList = DataManagerList.newInstance();
    private FormManagerList formManagerList = FormManagerList.newInstance();

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setTitle(getString(R.string.manage_files));
        setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.file_manager_layout);
        initToolbar();

        String[] tabNames = {getString(R.string.data), getString(R.string.forms)};
        // Get the ViewPager and set its PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(dataManagerList);
        fragments.add(formManagerList);

        viewPager.setAdapter(new ViewPagerAdapter(
                getSupportFragmentManager(), tabNames, fragments));

        // Give the SlidingTabLayout the ViewPager
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setFontColor(android.R.color.white);
        slidingTabLayout.setBackgroundColor(Color.DKGRAY);
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.notes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Collect.getInstance().getActivityLogger().logOnStart(this);
    }

    @Override
    protected void onStop() {
        Collect.getInstance().getActivityLogger().logOnStop(this);
        super.onStop();
    }
}