package com.jhughes.getstuffdone.dashboard.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import com.jhughes.getstuffdone.common.theme.GetStuffDoneTheme
import com.jhughes.getstuffdone.common.ui.StaggeredVerticalGrid

@Composable
fun GroupsList(
    modifier: Modifier = Modifier,
    gsdGroups: List<GsdGroup>,
    onOpenGroupDetails: (GsdGroup) -> Unit = {}
) {
    ScrollableColumn(modifier = modifier.padding(horizontal = 12.dp)) {

        val groupsPartition = gsdGroups.partition { it.isCompleted() }

        //uncompleted
        if (groupsPartition.second.isNotEmpty()) {
            Text(text = "Uncompleted", modifier = Modifier.padding(top = 24.dp, bottom = 12.dp))
            StaggeredVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                items = groupsPartition.second
            ) { item: GsdGroup ->
                GroupPreviewCard(
                    gsdGroup = item,
                    onClick = onOpenGroupDetails
                )
            }
        }

        //completed
        if (groupsPartition.first.isNotEmpty()) {
            Text(text = "Completed", modifier = Modifier.padding(top = 24.dp, bottom = 12.dp))
            StaggeredVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                items = groupsPartition.first
            ) { item: GsdGroup ->
                GroupPreviewCard(
                    gsdGroup = item,
                    onClick = onOpenGroupDetails
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupsListPreview() {
    GetStuffDoneTheme {
        Surface {
            GroupsList(gsdGroups = dummyData())
        }
    }
}

private fun dummyData(): List<GsdGroup> {

    val groupOne = GsdGroup(
        id = 1,
        title = "Shopping list",
        tasks = listOf(
            GsdTask(1, "Eggs", true),
            GsdTask(6, "Semi-skimmed Milk", true),
            GsdTask(3, "Butter", true),
            GsdTask(8, "English Muffin", false),
            GsdTask(9, "Bread", false),
            GsdTask(10, "Sweet Potatoes", false)
        )
    )

    val groupTwo = GsdGroup(
        id = 2,
        title = "Weekend prep",
        tasks = listOf(
            GsdTask(5, "Make a plan", false),
            GsdTask(2, "Tell people the plan", false),
            GsdTask(7, "Do the plan", false),
            GsdTask(4, "pat yourself on the back for a job well done", false)
        )
    )

    val groupThree = GsdGroup(
        id = 3,
        title = "Things I need to do for interview",
        tasks = listOf(
            GsdTask(11, "update my CV", true),
            GsdTask(12, "make my Github look great", true),
            GsdTask(13, "clean up my LinkedIn", true),
        )
    )

    val groupFour = GsdGroup(
        id = 4,
        title = "Christmas present Idea",
        tasks = listOf(
            GsdTask(14, "Christmas Jumper", false),
            GsdTask(15, "bottle of wine", false),
            GsdTask(16, "big speaker system", true),
            GsdTask(17, "new xbox", false),
        )
    )

    val groupFive = GsdGroup(
        id = 5,
        title = "Donzo list",
        tasks = listOf(
            GsdTask(18, "Did that thing", true),
            GsdTask(19, "made that thing", true),
            GsdTask(20, "washed that thing", true),
            GsdTask(21, "ate that thing", true),
            GsdTask(22, "listened to that thing", true),
        )
    )

    val groupSix = GsdGroup(
        id = 6,
        title = "Short list",
        tasks = listOf(
            GsdTask(23, "Did that thing", false),
            GsdTask(24, "made that thing", false),
        )
    )

    return listOf(groupOne, groupTwo, groupThree, groupFour, groupFive, groupSix)
}