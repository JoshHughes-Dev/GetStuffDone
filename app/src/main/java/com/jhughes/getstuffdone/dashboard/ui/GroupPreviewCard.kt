package com.jhughes.getstuffdone.dashboard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import com.jhughes.getstuffdone.ui.GetStuffDoneTheme

@Composable
fun GroupPreviewCard(
    modifier: Modifier = Modifier,
    gsdGroup: GsdGroup,
    onClick: (GsdGroup) -> Unit = {}
) {
    Card(
        modifier = modifier.clickable(onClick = { onClick(gsdGroup) }),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = gsdGroup.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Divider(modifier = Modifier.padding(top = 2.dp, bottom = 8.dp))

            val tasksPartition = gsdGroup.tasks.partition { gsdTask ->
                gsdGroup.tasks.indexOf(gsdTask) < 4
            }

            tasksPartition.first.forEachIndexed { index, task ->

                val topPadding = if (index == 0) 0.dp else 2.dp

                val checkboxColors = groupPreviewCheckBoxColors()

                Row(modifier = Modifier.padding(top = topPadding)) {
                    Checkbox(
                        modifier = Modifier.padding(3.dp).size(14.dp),
                        checked = task.isCompleted,
                        enabled = false,
                        colors = checkboxColors,
                        onCheckedChange = {})
                    Text(
                        text = task.description,
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }

            if (tasksPartition.second.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "+ ${tasksPartition.second.size} more items",
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
private fun groupPreviewCheckBoxColors() = CheckboxConstants.defaultColors(
    disabledColor = MaterialTheme.colors.secondary
)

private val dummyData1 = GsdGroup(
    title = "Shopping list",
    tasks = listOf(
        GsdTask(1, "Eggs", true),
        GsdTask(1, "Semi-skimmed Milk", true),
        GsdTask(1, "Butter", true),
        GsdTask(1, "English Muffin", false),
        GsdTask(1, "Bread", false),
        GsdTask(1, "Sweet Potatoes", false)
    )
)

private val dummyData2 = GsdGroup(
    title = "A really long name for a list",
    tasks = listOf(
        GsdTask(1, "something interesting but super long", false),
        GsdTask(1, "Hey mom, im on tv!!!!!", false)
    )
)

@Preview(widthDp = 100)
@Composable
fun GroupPreviewCardPreviewLightTheme_shortList() {
    GetStuffDoneTheme {
        GroupPreviewCard(gsdGroup = dummyData2)
    }
}


@Preview(widthDp = 100)
@Composable
fun GroupPreviewCardPreviewLightTheme_longList() {
    GetStuffDoneTheme {
        GroupPreviewCard(gsdGroup = dummyData1)
    }
}

@Preview(widthDp = 100)
@Composable
fun GroupPreviewCardPreviewDarkTheme() {
    GetStuffDoneTheme(darkTheme = true) {
        GroupPreviewCard(gsdGroup = dummyData1)
    }
}