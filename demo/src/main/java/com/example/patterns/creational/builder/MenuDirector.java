package com.example.patterns.creational.builder;

/**
 * Director para el patrón Builder
 * Construye menús predefinidos
 */
public class MenuDirector {
    
    public Menu createFamilyMenu() {
        return new Menu.Builder()
                .name("Menú Familiar")
                .addAppetizer("Nachos compartidos", 12.99)
                .addMainCourse("Pizza familiar", 24.99)
                .addMainCourse("Pasta para 2", 18.99)
                .addDessert("Helado variado", 8.99)
                .addBeverage("Jarra de limonada", 6.99)
                .addBeverage("Refresco grande", 4.99)
                .specialInstructions("Servir todos los platos al mismo tiempo")
                .build();
    }
    
    public Menu createVegetarianMenu() {
        return new Menu.Builder()
                .name("Menú Vegetariano")
                .addAppetizer("Ensalada verde", 7.99)
                .addMainCourse("Lasaña de vegetales", 14.99)
                .addDessert("Tarta de frutas", 6.99)
                .addBeverage("Jugo natural", 4.99)
                .vegetarian(true)
                .specialInstructions("Sin productos de origen animal")
                .build();
    }
    
    public Menu createGourmetMenu() {
        return new Menu.Builder()
                .name("Menú Gourmet")
                .addAppetizer("Carpaccio de salmón", 18.99)
                .addAppetizer("Foie gras", 22.99)
                .addMainCourse("Filet mignon", 42.99)
                .addDessert("Crème brûlée", 12.99)
                .addDessert("Petit fours", 8.99)
                .addBeverage("Vino tinto reserva", 35.99)
                .specialInstructions("Maridaje sugerido por el sommelier")
                .build();
    }
    
    public Menu createHealthyMenu() {
        return new Menu.Builder()
                .name("Menú Saludable")
                .addAppetizer("Sopa de verduras", 6.99)
                .addMainCourse("Pechuga de pollo a la plancha con quinoa", 16.99)
                .addDessert("Yogurt griego con frutas", 5.99)
                .addBeverage("Agua mineral", 2.99)
                .vegetarian(false)
                .glutenFree(true)
                .specialInstructions("Bajo en calorías y sodio")
                .build();
    }
}
