package com.aranirahan.moviecatalogue.viewmodel

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainViewModelTest{
    private var vmMain: MainViewModel? = null

    @Before
    fun setUp() {
        vmMain = MainViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun movies() {
        assertEquals(16, vmMain?.movies?.size)
    }
}