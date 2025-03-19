package com.raiffeisen.peopleagenda.presentation.agenda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.raiffeisen.peopleagenda.R
import com.raiffeisen.peopleagenda.common.hourAndMinutes
import com.raiffeisen.peopleagenda.domain.model.User
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

private const val BOTTOM_BUFFER_FOR_CONTINUOUS_SCROLLING = 3

@Composable
internal fun UsersAgendaScreen(
    viewModel: UsersAgendaViewModel = koinViewModel()
) {
    val state = viewModel.state.value
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .collectLatest { lastVisibleItemIndex ->
                if (lastVisibleItemIndex >= listState.layoutInfo.totalItemsCount - BOTTOM_BUFFER_FOR_CONTINUOUS_SCROLLING) {
                    viewModel.loadNextPage()
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            items(
                items = state.users,
                key = { user -> user.fistName + user.lastName }
            ) { user ->
                UserItem(
                    user = user,
                )
                HorizontalDivider()
            }
            if (state.isPartialLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

private const val ICON_SIZE = 20
private const val PROFILE_IMAGE_SIZE = 48

@Composable
private fun UserItem(user: User) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = user.profilePictureUrl),
            contentDescription = null,
            modifier = Modifier
                .clip(ShapeDefaults.ExtraLarge)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .size(PROFILE_IMAGE_SIZE.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "${user.fistName} ${user.lastName}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(
                text = "${user.age} years from ${user.country}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.attachment_24),
                    modifier = Modifier.size(ICON_SIZE.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = user.registeredDate.hourAndMinutes(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.star_24),
                modifier = Modifier.size(ICON_SIZE.dp),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersAgendaScreenPreview() {
    UsersAgendaScreen()
}