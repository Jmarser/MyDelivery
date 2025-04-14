package com.jmarser.mydelivery.presentation.components


import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun RowQuestionWithTextButton(
    modifier: Modifier = Modifier,
    @StringRes textQuestion: Int,
    @StringRes textOption: Int,
    @ColorRes colorQuestion: Color = Color.Black,
    @ColorRes colorOption: Color = Orange_enabled,
    onClickText: () -> Unit
) {

    Row (
        modifier = modifier
            .padding(bottom = MyDimens.dimens.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(id = textQuestion),
            color = colorQuestion
        )

        TextButton(
            onClick = onClickText
        ) {
            Text(
                text = stringResource(id = textOption),
                color = colorOption,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowQuestionWithTextButtonPreview() {
    RowQuestionWithTextButton(
        modifier = Modifier,
        textQuestion = R.string.alread_have_account,
        textOption = R.string.sign_in,
        onClickText = {}
    )
}
