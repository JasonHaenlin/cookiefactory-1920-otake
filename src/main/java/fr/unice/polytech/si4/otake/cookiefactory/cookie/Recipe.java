package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

public enum Recipe {
    // @formatter:off
    CHOCOCOLALALA("Chococolalala") {
        @Override
        public Cookie build() {
            return new Cookie("Chococolalala", 5.50,
                    Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED)
                    .addTopping(Topping.WHITECHOCOLATE)
                    .addTopping(Topping.WHITECHOCOLATE)
                    .addTopping(Topping.WHITECHOCOLATE)
                    .withFlavourType(Flavour.VANILLA)
                    .cook();
        }
    },
    DARKTEMPTATION("Dark Temptation") {
		@Override
		public Cookie build() {
            return new Cookie("Dark Temptation", 5.50, Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED)
                    .addTopping(Topping.MILKCHOCOLATE)
                    .addTopping(Topping.MILKCHOCOLATE)
                    .addTopping(Topping.WHITECHOCOLATE)
                    .withFlavourType(Flavour.CINNAMON)
                    .cook();
		}
    },
    SOOCHOCOLATE("Soooo Chocolate") {
		@Override
		public Cookie build() {
            return new Cookie("Soooo Chocolate", 5.50, Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED)
                    .addTopping(Topping.MILKCHOCOLATE)
                    .addTopping(Topping.MILKCHOCOLATE)
                    .addTopping(Topping.WHITECHOCOLATE)
                    .cook();
		}
    };

    // @formatter:on

    private String name;

    Recipe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Cookie build();
}
