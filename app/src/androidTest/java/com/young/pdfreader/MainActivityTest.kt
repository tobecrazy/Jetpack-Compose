package com.young.pdfreader

import android.os.SystemClock
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.young.pdfreader.data.ComponentItems
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](https://developer.android.com/jetpack/compose/interop/adding).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.young.pdfreader", appContext.packageName)
    }

    @Test
    fun testComposeUIComponent() {
        val titleStr = InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getString(R.string.title)
        composeTestRule.onNodeWithText(titleStr).assertIsDisplayed()
        //Reason: Expected exactly '1' node but found '2' nodes that satisfy:
        // (Text + EditableText contains 'IMAGE' (ignoreCase: false))
        composeTestRule.onAllNodesWithText(ComponentItems.TEXT.name).assertAll(hasClickAction())
        composeTestRule
            .onAllNodes(hasText(ComponentItems.TEXT.name)).filter(hasClickAction()).onLast()
            .performClick()
        SystemClock.sleep(1000)
        composeTestRule.onNodeWithText("Display String Directly").assertIsDisplayed()
    }

}