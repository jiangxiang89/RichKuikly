package com.leon.richkuikly

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.leon.richkuikly.base.BasePager
import com.tencent.kuikly.compose.foundation.layout.Column
import com.tencent.kuikly.compose.foundation.layout.fillMaxSize
import com.tencent.kuikly.compose.foundation.layout.fillMaxWidth
import com.tencent.kuikly.compose.foundation.layout.padding
import com.tencent.kuikly.compose.foundation.pager.HorizontalPager
import com.tencent.kuikly.compose.foundation.pager.rememberPagerState
import com.tencent.kuikly.compose.material3.Tab
import com.tencent.kuikly.compose.material3.TabRow
import com.tencent.kuikly.compose.material3.Text
import com.tencent.kuikly.compose.setContent
import com.tencent.kuikly.compose.ui.Modifier
import com.tencent.kuikly.compose.ui.unit.dp
import com.tencent.kuikly.core.annotations.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Page("router", supportInLocal = true)
internal class ComposeRoutePager : BasePager() {

    override fun willInit() {
        super.willInit()
        setContent {
            MainTabRow()
        }
    }

    @Composable
    private fun MainTabRow() {
        val tabs = listOf("首页", "Playground", "消息", "我的")
        val pagerState = rememberPagerState(pageCount = { tabs.size })
        val coroutineScope = rememberCoroutineScope()
        var textFieldValue by remember { mutableStateOf("") }

        LaunchedEffect(1) {
            withContext(Dispatchers.Main) {
                textFieldValue = ""
            }
        }
        Column(
            modifier =
                Modifier
                    .padding(top = pageData.statusBarHeight.dp)
                    .fillMaxSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().weight(1f),
                key = { index -> tabs[index] },
                userScrollEnabled = false
            ) {
                when (it) {
                    0 -> HomePageView()
                    1 -> PlaygroundView()
                    2 -> MessagePageView()
                    3 -> MinePageView()
                }
            }
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
                divider = { }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        },
                        text = { Text(title) },
                    )
                }
            }
        }

    }

}

@Composable
fun MinePageView() {
    Text("mine")
}


@Composable
fun MessagePageView() {
    Text("MessagePageView")
}
@Composable
fun PlaygroundView() {
    Text("PlaygroundView")
}


@Composable
fun HomePageView() {
    Text("HomePageView")
}