package com.ozbaykus.pagingexample.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ozbaykus.pagingexample.R
import com.ozbaykus.pagingexample.view.characters.CharactersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(CharactersListFragment(), R.id.frameLayoutContainer)
    }

    private fun replaceFragment(fragment: Fragment, container: Int) {
        val className = fragment.javaClass.name
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment, className)
            .commit()
    }
}