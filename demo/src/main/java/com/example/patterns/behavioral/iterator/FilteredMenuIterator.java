package com.example.patterns.behavioral.iterator;

import java.util.List;
import java.util.stream.Collectors;

public class FilteredMenuIterator implements MenuIterator {
    private List<DailySpecial> filteredSpecials;
    private int position = 0;

    public FilteredMenuIterator(List<DailySpecial> specials, String category) {
        this.filteredSpecials = specials.stream()
                .filter(s -> s.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return position < filteredSpecials.size();
    }

    @Override
    public DailySpecial next() {
        if (hasNext()) {
            return filteredSpecials.get(position++);
        }
        return null;
    }

    @Override
    public void reset() {
        position = 0;
    }
}
