package com.example.myboardgameapplication.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myboardgameapplication.databinding.FragmentFriendsBinding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* ... */ }) {
                            /* FAB content */
                        }
                    },
                    isFloatingActionButtonDocked = true
                ) {

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp)
                ) {
                    Button(onClick = { }) {
                        Text("ログアウト")
                    }
                }
            }
        }
    }
}
