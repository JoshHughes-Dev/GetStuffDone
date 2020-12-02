package com.jhughes.getstuffdone.common

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.jhughes.getstuffdone.domain.model.GsdGroup

sealed class Screen {

    abstract fun route(): String

    object Dashboard : Screen() {
        override fun route(): String = "dashboard"
    }

    object GroupDetails : Screen() {
        override fun route(): String = "groupdetails/{$ARGUMENT_GROUP_ID}"

        fun route(gsdGroup: GsdGroup): String = "groupdetails/${gsdGroup.id}"

        fun arguments(): List<NamedNavArgument> {
            return listOf(navArgument(ARGUMENT_GROUP_ID) { type = NavType.IntType })
        }

        const val ARGUMENT_GROUP_ID = "group_id"
    }
}