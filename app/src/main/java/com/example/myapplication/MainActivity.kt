package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(MyObserver())

        viewModel.info.observe(this){
            displaySnackbar(it)
        }

        binding.fab.setOnClickListener { view ->
            viewModel.loadData()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_share -> handleShare()
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun handleShare(): Boolean {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,
                    "I just purchased ${viewModel.info.value} bottles of olive oil"
                )
        }
        startActivity(intent)
        return true
    }

    private fun displaySnackbar(count: Int) {
        Snackbar.make(
            binding.root!!,
            "Current value $count",
            Snackbar.LENGTH_LONG
        ).show()
    }
}