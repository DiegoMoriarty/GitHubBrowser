package com.moriartys.githubbrowser.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.moriartys.githubbrowser.R
import com.moriartys.githubbrowser.viewModel.RepoListViewModel
import com.moriartys.githubbrowser.viewModel.RepoPresentationModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : SearchView.OnQueryTextListener, AppCompatActivity() {

    private var adapter: ReposAdapter? = null
    private val viewModel: RepoListViewModel by viewModel()
    private var snack: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel.list.observe(this, Observer<List<RepoPresentationModel>> { data ->
            adapter?.updateData(data)
        })
        viewModel.error.observe(this, Observer { error ->
            //TODO: show internationalised errors by category, or enumerated keyword...
            if (error != null) {
                snack = Snackbar.make(main, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
                snack?.show()
            } else {
                snack?.dismiss()
            }
        })

        adapter = ReposAdapter()
        recycler.adapter = adapter

        search.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.onSearchRequested(query!!)
        //Quick and easy way to prevent the keyboard from popping up on orientation change:
        recycler.requestFocus()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}

