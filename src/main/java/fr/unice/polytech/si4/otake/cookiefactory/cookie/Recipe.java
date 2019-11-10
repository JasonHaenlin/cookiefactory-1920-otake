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
        public Cookie create() {
            return new Cookie("Chococolalala",
                    Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED,Topping.WHITECHOCOLATE,Topping.WHITECHOCOLATE,Topping.WHITECHOCOLATE )
                    .withFlavourType(Flavour.VANILLA);
        }
    },
    DARKTEMPTATION("Dark Temptation") {
		@Override
		public Cookie create() {
            return new Cookie("Dark Temptation", Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED,Topping.MILKCHOCOLATE,Topping.MILKCHOCOLATE,Topping.WHITECHOCOLATE)
                    .withFlavourType(Flavour.CINNAMON);
		}
    },
    SOOCHOCOLATE("Soooo Chocolate") {
		@Override
		public Cookie create() {
            return new Cookie("Soooo Chocolate", Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED,Topping.MILKCHOCOLATE,Topping.MILKCHOCOLATE,Topping.WHITECHOCOLATE);
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

    public abstract Cookie create();
}
