package com.example.mykoinapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykoinapplication.R
import com.example.mykoinapplication.data.model.User
import com.example.mykoinapplication.util.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Here, by viewModels() creates the instance for the ViewModel
    // and it will also resolve the dependency required by it.
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    /**
     * Note: If we want to pass any dependency required by any class like Activity
     * we use by inject() also known as field injection.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setUpObserver()
    }

    private fun setupUI() {
        mainAdapter = MainAdapter(arrayListOf())

        findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).addItemDecoration(
            DividerItemDecoration(
                findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).context,
                (findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).layoutManager as LinearLayoutManager).orientation
            )
        )
        findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).adapter = mainAdapter
    }

    private fun setUpObserver() {
        mainViewModel.users.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                    findViewById<RecyclerView>(R.id.mainActivityRecyclerViewUsersInfo).visibility = View.GONE
                }
                Status.ERROR -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        mainAdapter.addData(users)
        mainAdapter.notifyDataSetChanged()
    }

}