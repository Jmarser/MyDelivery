package com.jmarser.mydelivery.presentation.feature_home


import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm

@Composable
fun CategoriesList(
    categories: List<CategoryDm>,
    selectedCategory: CategoryDm?,
    onCategorySelected: (CategoryDm) -> Unit
) {
    LazyRow {
        items(categories){category ->
            CategoryItem(
                modifier = Modifier
                    .animateItem(
                        fadeInSpec = tween(1000),
                        fadeOutSpec = tween(1000)
                    ),
                category = category,
                isSelected = category.id == selectedCategory?.id,
                onClick = {
                    onCategorySelected(category)
                }
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun CategoriesListPreview() {
    CategoriesList(
        categories = listOf(
            CategoryDm(
                id = "273ce30c-3b58-4965-b492-057e9e75c17c",
                name = "Asian Cuisine",
                imageUrl = "https://png.pngtree.com/png-vector/20231016/ourmid/pngtree-burger-food-png-free-download-png-image_10199386.png"
            ),
            CategoryDm(
                id = "273ce30c-3b58-4965-b492-057e9e75c17c",
                name = "Burger",
                imageUrl = "https://e7.pngegg.com/pngimages/706/98/png-clipart/japanese-cuisine-chinese-cuisine-vietnamese-cuisine-asian-cuisine-dish-cooking-leaf-vegetable-food.png"
            ),
            CategoryDm(
                id = "273ce30c-3b58-4965-b492-057e9e75c17c",
                name = "Pizza",
                imageUrl = "https://images.vexels.com/content/136312/preview/logo-pizza-fast-food-d65bfe.png"
            ),
            CategoryDm(
                id = "273ce30c-3b58-4965-b492-057e9e75c17c",
                name = "Healthy Food",
                imageUrl = "https://png.pngtree.com/png-clipart/20190516/original/pngtree-healthy-food-png-image_3776802.jpg"
            ),
            CategoryDm(
                id = "273ce30c-3b58-4965-b492-057e9e75c17c",
                name = "Beverages",
                imageUrl = "https://www.pngfind.com/pngs/m/172-1729150_alcohol-drinks-png-mojito-drink-transparent-png.png"
            )
        )
    )
}*/
