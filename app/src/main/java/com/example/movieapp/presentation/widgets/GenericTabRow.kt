package com.example.movieapp.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun GenericTabRow(
    modifier: Modifier = Modifier,
    tabRowElements: List<TabRowElement>,
    currentTabIndex: Int = 0,
    onTabChange: (Int) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = currentTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[currentTabIndex]),
                    color = Color.White
                )
            },
            containerColor = Color.Black,
            divider = {
                Divider(color = Color.Black)
            }
        ) {
            tabRowElements.forEach { tabRowElement ->
                Tab(
                    selected = currentTabIndex == tabRowElements.indexOf(tabRowElement),
                    onClick = { onTabChange(tabRowElements.indexOf(tabRowElement)) },
                    text = {
                        TabRowContent(
                            tabRowElement = tabRowElement,
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray
                )
            }
        }

        Divider(color = Color.Black)
    }
}

@Composable
fun TabRowContent(
    tabRowElement: TabRowElement,
) {
    Text(
        text = (tabRowElement.titleId?.let { stringResource(id = it) }).orEmpty(),
        overflow = TextOverflow.Ellipsis
    )
}

data class TabRowElement(
    @StringRes val titleId: Int? = null,
)