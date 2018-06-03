package com.braismoure.cantinamoseisley.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.model.Table

/**
 * Created by MoureDev by Brais Moure on 2/6/18.
 * www.mouredev.com
 */

class TableRecyclerViewAdapter(private val tables: List<Table>): RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_table, parent, false)

        // Notificamos al onClickListener
        view.setOnClickListener {
            onClickListener?.onClick(it)
        }

        return TableViewHolder(view)
    }

    override fun getItemCount() = tables.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bindTable(tables[position])
    }

    inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tableTitleText = itemView.findViewById<TextView?>(R.id.tableTitleTextView)
        //private val tableImage = itemView.findViewById<ImageView?>(R.id.tableImageView)
        private val tableNameText = itemView.findViewById<TextView?>(R.id.tableNameTextView)

        private val context = itemView.context

        fun bindTable(table: Table) {

            // Actualizamos la vista con el modelo
            tableTitleText?.text = String.format(context.getString(R.string.table_number), table.number)
            tableNameText?.text = table.name
        }
    }

}