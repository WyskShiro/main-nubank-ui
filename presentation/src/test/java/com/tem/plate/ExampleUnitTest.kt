package com.tem.plate

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.tem.plate._util.startMockedKoin
import com.tem.plate.container.ContainerActivity


/**
 * ApiPokemon local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PokemonListFragmentTest : KoinTest {

    @Before
    fun before() {
        if (GlobalContext.getOrNull() != null) {
            stopKoin()
        }
        startMockedKoin()
    }

    @Test
    fun checkSixthPokemon() {
        ActivityScenario.launch(ContainerActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assert(activity is ContainerActivity)
                onView(ViewMatchers.withId(R.id.text_pokemon_name)).check(ViewAssertions.matches(withText("1")))
            }
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}