package com.example.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Iterator
 * Colección de platos del día
 */
public class DailyMenuCollection {
    private List<DailySpecial> specials = new ArrayList<>();

    public void addSpecial(DailySpecial special) {
        specials.add(special);
    }

    public MenuIterator createIterator() {
        return new DailyMenuIterator(specials);
    }

    public MenuIterator createFilteredIterator(String category) {
        return new FilteredMenuIterator(specials, category);
    }
}
