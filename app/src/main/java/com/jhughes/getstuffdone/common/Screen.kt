package com.jhughes.getstuffdone.common

import androidx.navigation.NavBackStackEntry
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
        override fun route(): String = "groupdetails?groupId={$ARGUMENT_GROUP_ID}"

        fun routeWith(): String = "groupdetails"

        fun routeWith(gsdGroup: GsdGroup): String = "groupdetails?groupId=${gsdGroup.id}"

        fun arguments(): List<NamedNavArgument> {
            return listOf(navArgument(ARGUMENT_GROUP_ID) {
                type = NavType.StringType
                nullable = true
            })
        }

        fun retrieveGroupId(backStackEntry: NavBackStackEntry) : Int? {
            return backStackEntry.arguments?.getString(ARGUMENT_GROUP_ID)?.toInt()
        }

        const val ARGUMENT_GROUP_ID = "group_id"
    }
}