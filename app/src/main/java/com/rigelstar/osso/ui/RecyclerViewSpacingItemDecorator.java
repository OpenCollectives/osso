package com.rigelstar.osso.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewSpacingItemDecorator extends RecyclerView.ItemDecoration
{
    public static final int HORIZONTAL = 0x1;
    public static final int VERTICAL = 0x2;

    private final int orientation;
    private final int space;

    public RecyclerViewSpacingItemDecorator(int space, int orientation)
    {
        this.space = space;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(this.orientation == HORIZONTAL)
        {
            outRect.left = space;
            outRect.right = space;
        }
        else if(this.orientation == VERTICAL)
        {
            outRect.top = space;
            outRect.bottom = space;
        }
    }
}
