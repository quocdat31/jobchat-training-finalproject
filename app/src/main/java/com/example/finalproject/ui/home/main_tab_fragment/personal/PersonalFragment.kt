package com.example.finalproject.ui.home.main_tab_fragment.personal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personalFragmentLogOutButton.setOnClickListener {
            onButtonPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    private fun onButtonPressed() {
        listener?.onPersonalFragmentLogOutButtonClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onPersonalFragmentLogOutButtonClick()
    }

}
