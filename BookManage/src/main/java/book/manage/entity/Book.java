package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Book {
    int bid;
    String title;
    String description;
    double price;

    public Book(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
