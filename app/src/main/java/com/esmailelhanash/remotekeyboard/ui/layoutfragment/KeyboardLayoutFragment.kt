package com.esmailelhanash.remotekeyboard.ui.layoutfragment

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.esmailelhanash.remotekeyboard.ui.layoutfragment.composables.KeyboardLayoutRoot

// a fragment displaying a keyboard layout, and all its buttons
// with a tool bar button to add a new button to the keyboard layout
// it is full screen, and has a toolbar, and landscape
class KeyboardLayoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use ComposeView as the root view for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                KeyboardLayoutRoot(

                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    override fun onPause() {
        super.onPause()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR

    }

}