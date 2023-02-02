package com.onitsura12.cfttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onitsura12.cfttest.fragments.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }





    private fun initFragment(){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()

    }
}