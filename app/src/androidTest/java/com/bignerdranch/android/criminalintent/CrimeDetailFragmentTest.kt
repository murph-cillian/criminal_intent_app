package com.bignerdranch.android.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.Companion.launch
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeDetailBinding
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {
    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = launch(CrimeDetailFragment::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun solvedCheckBoxIsUncheckedOnLaunch() {
        scenario.onFragment { fragment ->
            val binding = FragmentCrimeDetailBinding.bind(fragment.requireView())
            assertFalse(binding.crimeSolved.isChecked)
        }
    }

//    @Test
//    fun showsFirstQuestionOnLaunch() {
//        onView(withId(R.id.question_text_view))
//            .check(matches(withText(R.string.question_australia)))
//    }

}