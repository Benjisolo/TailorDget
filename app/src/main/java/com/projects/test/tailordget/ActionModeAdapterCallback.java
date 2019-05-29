package com.projects.test.tailordget;

import java.util.List;

public interface ActionModeAdapterCallback<T> {

    void toggleSelection(int position);
    void clearSelections();
    int getSelectedCount();
    List<T> getSelectedItems();
}
