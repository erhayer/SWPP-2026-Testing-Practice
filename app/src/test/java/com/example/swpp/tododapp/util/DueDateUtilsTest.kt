package com.example.swpp.tododapp.util

import com.example.swpp.tododapp.tasks.dueDateLabel
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class DueDateUtilsTest {

    @Test
    fun dueDateLabel_activeTaskWithoutDueDate_returnsEmptyString() {
        // Given
        val dueDate: LocalDate? = null
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("", result)
    }

    @Test
    fun dueDateLabel_completedTaskWithoutDueDate_returnsClear() {
        // Given
        val dueDate: LocalDate? = null
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = true)

        // Then
        assertEquals("CLEAR", result)
    }

    @Test
    fun dueDateLabel_completedTaskWithPastDueDate_returnsClear() {
        // Given
        val dueDate = LocalDate.parse("2026-10-13")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = true)

        // Then
        assertEquals("CLEAR", result)
    }

    @Test
    fun dueDateLabel_dueToday_returnsDDay() {
        // Given
        val dueDate = LocalDate.parse("2026-10-14")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D-Day", result)
    }

    @Test
    fun dueDateLabel_dueTomorrow_returnsDMinus1() {
        // Given
        val dueDate = LocalDate.parse("2026-10-15")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D-1", result)
    }

    @Test
    fun dueDateLabel_dueInTwoDays_returnsDMinus2() {
        // Given
        val dueDate = LocalDate.parse("2026-10-16")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D-2", result)
    }

    @Test
    fun dueDateLabel_dueYesterday_returnsDPlus1() {
        // Given
        val dueDate = LocalDate.parse("2026-10-13")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D+1", result)
    }

    @Test
    fun dueDateLabel_dueTwoDaysAgo_returnsDPlus2() {
        // Given
        val dueDate = LocalDate.parse("2026-10-12")
        val today = LocalDate.parse("2026-10-14")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D+2", result)
    }

    @Test
    fun dueDateLabel_leapDayTomorrow_returnsDMinus1() {
        // Given
        val dueDate = LocalDate.parse("2024-02-29")
        val today = LocalDate.parse("2024-02-28")

        // When
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // Then
        assertEquals("D-1", result)
    }
}
