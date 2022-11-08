package by.pintusau.country.number.resolver.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUtils<T> {

    public List<T> getListDiff(List<T> first, List<T> second) {
        List<T> differences = new ArrayList<>(first);
        differences.removeAll(second);
        return differences;
    }
}
