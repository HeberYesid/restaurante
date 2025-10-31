package com.example.patterns.behavioral.iterator;

import java.util.List;

public class DailyMenuIterator implements MenuIterator {
    private List<DailySpecial> specials;
    private int position = 0;

    public DailyMenuIterator(List<DailySpecial> specials) {
        this.specials = specials;
    }

    @Override
    public boolean hasNext() {
        return position < specials.size();
    }

    @Override
    public DailySpecial next() {
        if (hasNext()) {
            return specials.get(position++);
        }
        return null;
    }

    @Override
    public void reset() {
        position = 0;
    }
}
