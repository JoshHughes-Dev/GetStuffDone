package com.jhughes.getstuffdone.common

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import com.jhughes.getstuffdone.common.utils.AmbientBackDispatcher
import com.jhughes.getstuffdone.ui.GetStuffDoneTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun GetStuffDoneApp(
    backDispatcher: OnBackPressedDispatcher,
    content: @Composable () -> Unit
) {
    GetStuffDoneTheme {
        Providers(AmbientBackDispatcher provides backDispatcher) {
            ProvideWindowInsets {
                content()
            }
        }
    }
}