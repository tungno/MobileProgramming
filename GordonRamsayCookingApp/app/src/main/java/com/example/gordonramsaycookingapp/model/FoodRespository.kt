package com.example.gordonramsaycookingapp.model

import com.example.gordonramsaycookingapp.R

object FoodRepository {
    private val foodList = listOf(
        Food(
            "1",
            "Beef Wellington",
            R.drawable.beef_wellington,
            "A classic British dish featuring tender fillet steak coated with pâté and duxelles (a finely chopped mixture of mushrooms, onions, and herbs), all wrapped in puff pastry and baked until golden brown."
        ),
        Food(
            "2",
            "Scrambled Eggs",
            R.drawable.scrambled_eggs,
            "Fluffy and creamy scrambled eggs made with farm-fresh eggs, gently cooked to perfection and seasoned with a touch of salt and freshly ground black pepper."
        ),
        Food(
            "3",
            "Lamb Shank",
            R.drawable.lamb_shank,
            "Slow-braised lamb shank cooked until the meat is tender and falls off the bone, infused with robust flavors of red wine, garlic, and rosemary."
        ),
        Food(
            "4",
            "Risotto",
            R.drawable.risotto,
            "A creamy Italian rice dish prepared with Arborio rice slowly cooked in broth, enriched with Parmesan cheese, and complemented by ingredients like wild mushrooms or asparagus."
        ),
        Food(
            "5",
            "Salmon Fillet",
            R.drawable.salmon_fillet,
            "Pan-seared salmon fillet with a crispy skin, served with a zesty lemon butter sauce and garnished with fresh dill and a sprinkle of sea salt."
        )
    )

    fun getFoodList(): List<Food> = foodList

    fun getFoodById(id: String): Food? = foodList.find { it.id == id }
}
