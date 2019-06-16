package pattararittigul.sasin.extensionlibrary.extension

import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

infix fun Context.setupRecyclerViewWithDivider(recyclerView: RecyclerView): RecyclerView {
    val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
    return setupRecyclerViewWithoutDivider(recyclerView.apply {
        addItemDecoration(divider, 0)
    })
}

infix fun Context.setupRecyclerViewWithoutDivider(recyclerView: RecyclerView): RecyclerView {
    val layoutManager = LinearLayoutManager(this)
    return recyclerView.apply {
        setHasFixedSize(true)
        itemAnimator = DefaultItemAnimator()
        setLayoutManager(layoutManager)
    }
}