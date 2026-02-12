package com.education.institutionmanagement.datapool;

import com.education.institutionmanagement.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class DataPoolService<T> {

    public List<T> search(List<T> source, Predicate<T> filter) {
        return source.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public List<T> sort(List<T> source, Comparator<T> comparator) {
        return source.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Person> sortPersonsByName(List<? extends Person> persons) {
        return persons.stream()
                .sorted(Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
}
