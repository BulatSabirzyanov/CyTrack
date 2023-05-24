package com.example.cytrack.presentation.screens.searchfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cytrack.R


class SearchFragment : Fragment(R.layout.fragment_search) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {


    }



    companion object {

        fun newInstance() = SearchFragment()
    }
}
