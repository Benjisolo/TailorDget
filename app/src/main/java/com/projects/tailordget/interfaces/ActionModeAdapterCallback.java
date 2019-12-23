package com.projects.tailordget.interfaces;

import java.util.List;

public interface ActionModeAdapterCallback<T> {

    void toggleSelection(int position);
    void clearSelections();
    int getSelectedCount();
    List<T> getSelectedItems();
}
