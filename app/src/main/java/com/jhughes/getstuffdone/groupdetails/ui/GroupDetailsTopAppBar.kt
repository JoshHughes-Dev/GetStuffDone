package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.getstuffdone.common.theme.GetStuffDoneTheme
import com.jhughes.getstuffdone.common.theme.progressIndicatorBackground

@OptIn(ExperimentalLayout::class)
@Composable
fun GroupDetailsTopAppBar(
    modifier: Modifier = Modifier,
    elevation : Dp = 0.dp,
    tasksCompletedCount: Int? = null,
    totalTasksCount: Int? = null,
    onBackPressed: () -> Unit = {},
    onDeleteGroup: () -> Unit = {}
) {
    TopAppBar(modifier = modifier, elevation = elevation) {
        Row(
            modifier = Modifier.fillMaxHeight().preferredWidth(68.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Providers(AmbientContentAlpha provides ContentAlpha.high) {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ArrowBack)
                }
            }
        }
        if (totalTasksCount != null && tasksCompletedCount != null) {
            ConstraintLayout(Modifier.fillMaxHeight().weight(1f)) {
                val (title, progress) = createRefs()
                createVerticalChain(title, progress, chainStyle = ChainStyle.Packed)
                TopAppBarTitle(
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(progress.top, margin = 8.dp)
                    },
                    tasksCompletedCount = tasksCompletedCount,
                    totalTasksCount = totalTasksCount
                )
                LinearProgressIndicator(
                    progress = (tasksCompletedCount) / totalTasksCount.toFloat(),
                    modifier = Modifier.fillMaxWidth().constrainAs(progress) {
                        top.linkTo(title.bottom)
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                    },
                    color = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.progressIndicatorBackground
                )
            }
            Row(
                modifier = Modifier.fillMaxHeight().preferredWidth(68.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Providers(AmbientContentAlpha provides ContentAlpha.high) {
                    IconButton(onClick = onDeleteGroup) {
                        Icon(imageVector = Icons.Default.DeleteForever)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppBarTitle(
    tasksCompletedCount: Int,
    totalTasksCount: Int,
    modifier: Modifier = Modifier
) {
    val indexStyle = MaterialTheme.typography.caption.toSpanStyle().copy(
        fontWeight = FontWeight.Bold
    )
    val totalStyle = MaterialTheme.typography.caption.toSpanStyle()
    val text = annotatedString {
        withStyle(style = indexStyle) {
            append(tasksCompletedCount.toString())
        }
        withStyle(style = totalStyle) {
            append(" of $totalTasksCount completed")
        }
    }
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        modifier = modifier
    )
}


@Preview
@Composable
fun GroupDetailsTopAppBarPreview() {
    GetStuffDoneTheme {
        GroupDetailsTopAppBar(tasksCompletedCount = 4, totalTasksCount = 10)
    }
}