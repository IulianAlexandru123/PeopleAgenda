package com.raiffeisen.peopleagenda.presentation.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.raiffeisen.peopleagenda.R

@Composable
fun UsersAgendaScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        UserItem()
        HorizontalDivider()
    }
}

private const val ICON_SIZE = 24

@Composable
private fun UserItem() {
    Row(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = "https://randomuser.me/api/portraits/thumb/women/88.jpg",
            contentDescription = null,
            modifier = Modifier
                .clip(ShapeDefaults.Medium)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        Column {
            Text(
                text = "Scott Ernest",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(
                text = "28 years from US",
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
                    text = "13:45",
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