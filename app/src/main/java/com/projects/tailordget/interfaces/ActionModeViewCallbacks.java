package com.projects.tailordget.interfaces;

public interface ActionModeViewCallbacks {
    void onListItemSelect(final int position);
    void onDestroyActionMode();
    void onDeleteActionClicked();
    void onFavoriteActionClicked();
}
