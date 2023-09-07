package com.example.coding10

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

//생성자로 listview adapter가 구현한 interface를 생성자로 받아 swipte한 대상을 전달한다
class MainListItemHelper(val con: Context): ItemTouchHelper.Callback() {

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
//        Log.d(TAG, "Orign position : $dX,$dY")
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = (viewHolder as MainListAdapter.MainItemViewHolder).binding.layoutItem
            getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
//            Log.d(TAG, "moved position : $dX,$dY")
        }
    }

    //상호작용 종료 및 애니메이션 종료 후 호출
    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ) {
        getDefaultUIUtil().clearView((viewHolder as MainListAdapter.MainItemViewHolder).binding.layoutItem)
    }

    //드래그 및 스와이프 방향을 제어. 드래그는 사용하지 않고, 양방향 스와이프를 사용한다.
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }

    //Swipe시 이벤트
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val itemViewHolder = viewHolder as MainListAdapter.MainItemViewHolder

        if(direction == ItemTouchHelper.RIGHT){
            val callUriSwipedPerson = Uri.parse("tel:${viewHolder.binding.tvNumber.text}")
            val callIntent = Intent(Intent.ACTION_CALL, callUriSwipedPerson)
            con.startActivity(callIntent)

            //스와이프 처리 후 제자리로 돌려놓는다
            (viewHolder.itemView.parent as RecyclerView).adapter?.notifyItemChanged(viewHolder.adapterPosition)
        }
    }

}
