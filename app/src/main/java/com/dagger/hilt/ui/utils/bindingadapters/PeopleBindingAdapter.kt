package com.dagger.hilt.ui.utils.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dagger.hilt.data.models.People
import com.dagger.hilt.ui.utils.ImageUtils

object PeopleBindingAdapter {

    @BindingAdapter("bindPeopleName")
    @JvmStatic
    fun TextView.bindPeopleName(people: People) {
        text = people.firstName.plus(" ").plus(people.lastName)
    }

    @BindingAdapter("bindPeopleEmail")
    @JvmStatic
    fun TextView.bindPeopleEmail(people: People) {
        text = people.email
    }

    @BindingAdapter("bindPeopleJobTitle")
    @JvmStatic
    fun TextView.bindPeopleJobTitle(people: People) {
        text = people.jobTitle
    }

    @BindingAdapter("bindPeopleAvatar")
    @JvmStatic
    fun ImageView.bindPeopleAvatar(people: People) {
        ImageUtils.loadFromUrl(people.avatar, this)
    }
}
