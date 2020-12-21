package com.jhughes.getstuffdone.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    items : List<T>,
    itemContent : @Composable ColumnScope.(item : T) -> Unit,
) {
    val itemsSplit = items.withIndex()
        .groupBy { it.index % 2 }
        .map { entry -> entry.value.map { it.value } }

    Row(modifier) {
        Box(Modifier.weight(1f)) {
            Column(Modifier.fillMaxWidth()) {
                itemsSplit.getOrNull(0)?.forEachIndexed { index, item ->
                    if (index != 0) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    itemContent(item)
                }
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Box(Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                itemsSplit.getOrNull(1)?.forEachIndexed { index, item ->
                    if (index != 0) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    itemContent(item)
                }
            }
        }
    }
}

