package com.fastsearchindexscroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.fastsearchindexscroller.util.StringMatcher;
import com.fastsearchindexscroller.widget.IndexableListView;

public class SearchScrollerActivity extends Activity {

	private ArrayList<String> mItems;
	private IndexableListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_scroller);

		mItems = new ArrayList<String>();

		mItems.add("Academic and Professional");
		mItems.add("Coffee Table");
		mItems.add("School Books");
		mItems.add("11/22/63");
		mItems.add("Travel");
		mItems.add("Biographies & Autobiographies");
		mItems.add("Religion & Spirituality");
		mItems.add("Arts, Photography and Design");
		mItems.add("Families and Relationships");
		mItems.add("Computers & Internet");
		mItems.add("Business, Investing and Management");
		mItems.add("Science & Technology");
		mItems.add("Entrance Exams Preparation");
		mItems.add("Health and Fitness");
		mItems.add("Music, Films and Entertainment");
		mItems.add("Philosophy");
		mItems.add("Outdoors & Nature");

		Collections.sort(mItems);
		ContentAdapter adapter = new ContentAdapter(this, android.R.layout.simple_list_item_1, mItems);

		mListView = (IndexableListView) findViewById(R.id.listview);
		mListView.setAdapter(adapter);
		mListView.setFastScrollEnabled(true);

	}

	private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {
		private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		public ContentAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public int getPositionForSection(int section) {
			// If there is no item for current section, previous section will be
			// selected
			for (int i = section; i >= 0; i--) {
				for (int j = 0; j < getCount(); j++) {
					if (i == 0) {
						// For numeric section
						for (int k = 0; k <= 9; k++) {
							if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
								return j;
						}
					} else {
						if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
							return j;
					}
				}
			}
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {

			String[] sections = new String[mSections.length()];
			for (int i = 0; i < mSections.length(); i++)
				sections[i] = String.valueOf(mSections.charAt(i));

			return sections;

		}
	}
}
