package com.example.finalproject.ui.home.main_tab_fragment.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.contactPresenter
import com.example.finalproject.model.Friend
import com.example.finalproject.ultis.ValidationCheck
import com.example.finalproject.ultis.disable
import com.example.finalproject.ultis.onTextChanged
import com.example.finalproject.ultis.toast
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.fragment_contact.*


class ContactFragment : Fragment(), ContactContract.View, OnRecyclerViewItemClickListener {

    private val mPresenter by lazy { contactPresenter() }
    private val REQUEST_ERROR = "Can not send add friend request for yourself"
    lateinit var mContactList: ArrayList<Friend>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    override fun onAddFriendButtonClick() {
        setVisibilityWhenAddingFriend(true)
        mPresenter.addFriend()
    }

    override fun onAddFriendSuccess() {
        setVisibilityWhenAddingFriend(false)
        activity?.applicationContext?.toast(getString(R.string.onSuccess))
        searchFriendEditText.text?.clear()
    }

    override fun onAddFriendError(exception: String?) {
        setVisibilityWhenAddingFriend(false)
        activity?.applicationContext?.toast("$exception")
    }

    override fun onSearchInputError() {

    }

    override fun onGetContactSuccess(friendList: ArrayList<Friend>) {
        loadingContactListProgressBar.visibility = View.GONE
        contactRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ContactRecyclerViewAdapter(
                friendList,
                activity?.applicationContext,
                ContactFragment()
            )
            visibility = View.VISIBLE
        }
    }

    override fun onGetContactError(databaseError: DatabaseError?) {
        activity?.applicationContext?.toast(databaseError?.message.toString())
    }

    override fun onItemClick(friend: Friend) {
        Log.d("asd", friend.email.toString())
    }

    private fun init() {
        loadingContactListProgressBar.visibility = View.VISIBLE

        mPresenter.setView(this)
        mPresenter.getContactList(mPresenter.getCurrentUserId())

        searchFriendEditText.onTextChanged { email ->
            addFriendButton.isEnabled = email != mPresenter.getCurrentUserEmail()

            if (!ValidationCheck.isEmailValid(email)) {
                searchFriendEditText.setError(getString(R.string.emailInputError), null)
                addFriendButton.disable()
            }
            mPresenter.onSearchInputChange(email)
        }

        addFriendButton.setOnClickListener {
            onAddFriendButtonClick()
        }

    }

    private fun setVisibilityWhenAddingFriend(isProcessing: Boolean) {

        if (isProcessing) {
            addingFriendProgressBar.visibility = View.VISIBLE
            addFriendButton.visibility = View.GONE
        } else {
            addingFriendProgressBar.visibility = View.GONE
            addFriendButton.visibility = View.VISIBLE
        }

    }
}

