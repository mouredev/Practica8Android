package com.braismoure.cantinamoseisley.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.adapter.TableRecyclerViewAdapter
import com.braismoure.cantinamoseisley.model.Table
import com.braismoure.cantinamoseisley.model.Tables
import kotlinx.android.synthetic.main.activity_tables.*

/**
 * Created by MoureDev by Brais Moure on 2/6/18.
 * www.mouredev.com
 */

class TablesActivity : AppCompatActivity() {

    private var tables: List<Table>? = null
        set(value) {
            field = value

            if (value != null) {
                val adapter = TableRecyclerViewAdapter(value)
                tables_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        title = getString(R.string.app_name)

        // Data
        tables_list.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.tables_columns))
        tables_list.itemAnimator = DefaultItemAnimator()
        tables = Tables.list()
    }

    fun setRecyclerViewClickListener() {

        // Si alguien pulsa un elemento del RecyclerView
        val adapter = tables_list?.adapter as? TableRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {

            val tableIndex = tables_list.getChildAdapterPosition(it)

            val intent = TableActivity.intent(this, tableIndex)
            startActivity(intent)
        }
    }

}
