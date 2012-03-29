/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.demo.notepad1;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Path.FillType;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class Notepadv1 extends ListActivity {
    private int mNoteNumber = 1;
    private NotesDbAdapter myDBAdapter;
    public static final int INSERT_ID = Menu.FIRST;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad_list);
        myDBAdapter = new NotesDbAdapter(this);
        myDBAdapter.open();
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        return result;
    }

    @Override
        // TODO Auto-generated method stub
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
        case INSERT_ID:
        	//createNote()
        	return true;
        }
    
        return super.onOptionsItemSelected(item);
    }
    
    
    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = myDBAdapter.fetchAllNotes();
        
        getLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>() {

			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoaderReset(Loader<Cursor> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //startManagingCursor(c);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.text1 };
        
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
            new SimpleCursorAdapter(this, R.layout.notes_rows, c, from, to);
        setListAdapter(notes);
    }
}
