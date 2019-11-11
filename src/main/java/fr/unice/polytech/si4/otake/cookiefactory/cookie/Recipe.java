package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import java.util.Arrays;

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
            return new Cookie("Chococolalala", Arrays.asList(Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED,Topping.WHITECHOCOLATE,Topping.WHITECHOCOLATE,Topping.WHITECHOCOLATE,Flavour.VANILLA)
                     );
        }
    },
    DARKTEMPTATION("Dark Temptation") {
		@Override
		public Cookie create() {
            return new Cookie("Dark Temptation",Arrays.asList(Cooking.CRUNCHY, Dough.CHOCOLATE, Mix.MIXED,Topping.MILKCHOCOLATE,Topping.MILKCHOCOLATE,Topping.WHITECHOCOLATE,Flavour.CINNAMON));
        }
    },
    SOOCHOCOLATE("Soooo Chocolate") {
		@Override
		public Cookie create() {
            return new Cookie("Soooo Chocolate", Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED,Topping.MILKCHOCOLATE,Topping.MILKCHOCOLATE,Topping.WHITECHOCOLATE));
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
