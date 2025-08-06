package ua.comparus.dataaggregator.model;

import java.util.Map;

public record User(
        String id,
        String username,
        String name,
        String surname
) {
    public static User fromRow(Map<String, Object> row, Map<String, String> mapping) {
        return new User(
                String.valueOf(row.get(mapping.get("id"))),
                String.valueOf(row.get(mapping.get("username"))),
                String.valueOf(row.get(mapping.get("name"))),
                String.valueOf(row.get(mapping.get("surname")))
        );
    }
}