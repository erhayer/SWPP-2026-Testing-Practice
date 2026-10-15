package com.example.swpp.tododapp.tasks

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.swpp.tododapp.data.Task
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksListBindingsTest {

    @Test
    fun setDueDateLabel_nullTask_preservesTextAndVisibility() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task: Task? = null

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("Old label", textView.text.toString())
            assertEquals(View.GONE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_activeTaskWithoutDueDate_preservesTextAndVisibility() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = null)

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("Old label", textView.text.toString())
            assertEquals(View.GONE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_dueToday_setsDDayAndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = LocalDate.now())

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("D-Day", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_dueTomorrow_setsDMinus1AndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = LocalDate.now().plusDays(1))

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("D-1", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_dueInTwoDays_setsDMinus2AndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = LocalDate.now().plusDays(2))

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("D-2", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_dueYesterday_setsDPlus1AndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = LocalDate.now().minusDays(1))

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("D+1", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_dueTwoDaysAgo_setsDPlus2AndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = false, dueDate = LocalDate.now().minusDays(2))

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("D+2", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_completedTaskWithoutDueDate_setsClearAndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = true, dueDate = null)

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("CLEAR", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }

    @Test
    fun setDueDateLabel_completedTaskWithDueDate_setsClearAndShowsView() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given
            val textView = TextView(ApplicationProvider.getApplicationContext<Context>())
            textView.text = "Old label"
            textView.visibility = View.GONE
            val task = Task(isCompleted = true, dueDate = LocalDate.now().plusDays(1))

            // When
            setDueDateLabel(textView, task)

            // Then
            assertEquals("CLEAR", textView.text.toString())
            assertEquals(View.VISIBLE, textView.visibility)
        }
    }
}
