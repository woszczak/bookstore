package pl.edu.wszib.bookstore.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    private Class clazz;
    private Long id;

    public NotFound(Long id, Class<?> clazz) {
        this.clazz = clazz;
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotFound notFound = (NotFound) o;
        return Objects.equals(clazz, notFound.clazz) && Objects.equals(id, notFound.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, id);
    }
}
