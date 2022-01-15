package dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.helper;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.adapter.MainAdapter;

public class RecyclerViewToachHolder extends ItemTouchHelper.SimpleCallback {
    RecyclerItemTouchHelperListener listener;

    public RecyclerViewToachHolder(int dragDirs,int swipeDirs,RecyclerItemTouchHelperListener listener){
        super(dragDirs,swipeDirs);
        this.listener=listener;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder!=null){
            final View foregroundView=((MainAdapter.CallViewHolder) viewHolder).view_foreground;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState,
                                boolean isCurrentlyActive) {
        final View foregroundView=((MainAdapter.CallViewHolder) viewHolder).view_foreground;

        getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX/2,dY,actionState,isCurrentlyActive);

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView=((MainAdapter.CallViewHolder) viewHolder).view_foreground;

        getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX/2,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final View foregroundView=((MainAdapter.CallViewHolder) viewHolder).view_foreground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }



    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
