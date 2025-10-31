package com.example.patterns.behavioral.templatemethod;

public class PastaPreparation extends MealPreparation {
    private boolean withSauce;

    public PastaPreparation(boolean withSauce) {
        this.withSauce = withSauce;
    }

    @Override
    protected void gatherIngredients() {
        System.out.println("Reuniendo: pasta, agua, sal, aceite");
        if (withSauce) {
            System.out.println("También reuniendo ingredientes para salsa");
        }
    }

    @Override
    protected void prepareIngredients() {
        System.out.println("Midiendo porciones de pasta");
    }

    @Override
    protected void cook() {
        System.out.println("Hirviendo agua y cocinando pasta por 8-10 minutos");
        if (withSauce) {
            System.out.println("Preparando salsa simultáneamente");
        }
    }

    @Override
    protected boolean customerWantsCondiments() {
        return withSauce;
    }

    @Override
    protected void addCondiments() {
        System.out.println("Agregando salsa y queso parmesano");
    }

    @Override
    protected void serve() {
        System.out.println("Sirviendo pasta caliente en plato hondo");
    }
}
