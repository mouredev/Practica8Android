package com.braismoure.cantinamoseisley.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.model.Menus
import com.braismoure.cantinamoseisley.model.Restaurant
import com.braismoure.cantinamoseisley.model.Tables

class MenuDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_MENU_INDEX = "EXTRA_MENU_INDEX"
        val EXTRA_DELETE_MENU = "EXTRA_DELETE_MENU"
        val EXTRA_NOTE = "EXTRA_NOTE"
        val EXTRA_MENU_POSITION = "EXTRA_MENU_POSITION"

        fun intent(context: Context, tableIndex: Int, menuIndex: Int, deleteMode: Boolean, note: String?, menuPosition: Int): Intent {
            val intent = Intent(context, MenuDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            intent.putExtra(EXTRA_MENU_INDEX, menuIndex)
            intent.putExtra(EXTRA_DELETE_MENU, deleteMode)
            intent.putExtra(EXTRA_NOTE, note)
            intent.putExtra(EXTRA_MENU_POSITION, menuPosition)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val table = Tables.getTable(intent.getIntExtra(EXTRA_TABLE_INDEX, 0))
        val menu = Menus.getMenu(intent.getIntExtra(EXTRA_MENU_INDEX, 0))
        val delete = intent.getBooleanExtra(EXTRA_DELETE_MENU, false)
        val note = intent.getStringExtra(EXTRA_NOTE)
        val menuPosition = intent.getIntExtra(EXTRA_MENU_POSITION, 0)

        val descriptionTextView = findViewById<TextView>(R.id.menuDescriptionTextView)
        val imageView = findViewById<ImageView>(R.id.menuImageView)
        val creditsTextView = findViewById<TextView>(R.id.menuCreditsTextView)
        val allergensView = findViewById<LinearLayout>(R.id.allergensView)
        val allergen1ImageView = findViewById<ImageView>(R.id.allergen1ImageView)
        val allergen2ImageView = findViewById<ImageView>(R.id.allergen2ImageView)
        val actionButton = findViewById<Button>(R.id.menuActionButton)
        val notesEditText = findViewById<EditText>(R.id.menuNotesEditText)
        if(delete) {
            notesEditText.isEnabled = false
            notesEditText.setText(note)
            actionButton.text = getString(R.string.remove)
        } else {
            actionButton.text = getString(R.string.add)
        }

        title = menu.title
        descriptionTextView?.text = menu.description
        creditsTextView?.text = String.format(getString(R.string.menu_credits), menu.price.toString())
        imageView?.setImageResource(menu.photoSrc)

        allergensView?.visibility = View.GONE
        allergen1ImageView?.visibility = View.INVISIBLE
        allergen2ImageView?.visibility = View.INVISIBLE
        menu.allergens?.let { allergens ->
            allergensView?.visibility = View.VISIBLE
            for ((index, allergen) in allergens.withIndex()) {
                if(index == 0) {
                    allergen1ImageView?.setImageResource(allergen.icoSrc)
                    allergen1ImageView?.visibility = View.VISIBLE
                } else if(index == 1) {
                    allergen2ImageView?.setImageResource(allergen.icoSrc)
                    allergen2ImageView?.visibility = View.VISIBLE
                }
            }
        }

        actionButton.setOnClickListener {
            if(delete) {
                Restaurant.removeMenu(table, menuPosition)
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
            } else {
                Restaurant.addMenu(table, menu, notesEditText.text.toString())
            }

            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
