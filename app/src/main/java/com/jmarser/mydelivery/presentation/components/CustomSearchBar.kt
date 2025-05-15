package com.jmarser.mydelivery.presentation.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    onClearQuery: () -> Unit
) {

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(MyDimens.dimens.paddingNormal),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MyDimens.dimens.paddingNormal)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.small
                    )
                    .border(
                        width = MyDimens.dimens.borderExtraSmall,
                        color = Color.Gray,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(MyDimens.dimens.paddingNormal),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MyDimens.dimens.paddingNormal)
            ) {
                Icon(
                    imageVector = AppIcons.ic_searchbar,
                    contentDescription = "search icon",
                    tint = Color.Gray
                )

                BasicTextField(
                    modifier = Modifier
                        .weight(1f),
                    state = state
                )
            }

            AnimatedVisibility(
                visible = state.text.isNotEmpty()
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onClearQuery()
                        },
                    imageVector = AppIcons.ic_clear,
                    contentDescription = "clear icon",
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CustomSearchBarPreview() {
    CustomSearchBar(
        modifier = Modifier,
        state = TextFieldState(),
        onClearQuery = {}
    )
}
