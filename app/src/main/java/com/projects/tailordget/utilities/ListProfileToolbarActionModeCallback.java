package com.projects.tailordget.utilities;

import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.projects.tailordget.interfaces.ActionModeViewCallbacks;
import com.projects.test.tailordget.R;

public class ListProfileToolbarActionModeCallback implements ActionMode.Callback {
    private final ActionModeViewCallbacks actionModeViewCallbacks;
    private final ProfileListAdapter profileListAdapter;

    public ListProfileToolbarActionModeCallback(final ActionModeViewCallbacks actionModeViewCallbacks, final ProfileListAdapter profileListAdapter) {
        this.actionModeViewCallbacks = actionModeViewCallbacks;
        this.profileListAdapter = profileListAdapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.profiles_content_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        menu.findItem(R.id.menuDelete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        boolean flag = false;
        switch (menuItem.getItemId()) {
            case R.id.menuDelete: {
                actionModeViewCallbacks.onDeleteActionClicked();
                actionMode.finish();
                flag = true;
            }break;
            case R.id.menuFavorite: {
                actionModeViewCallbacks.onFavoriteActionClicked();
                actionMode.finish();
                flag = true;
            }break;
        }
        return flag;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        actionModeViewCallbacks.onDestroyActionMode();
        profileListAdapter.clearSelections();
    }
}
