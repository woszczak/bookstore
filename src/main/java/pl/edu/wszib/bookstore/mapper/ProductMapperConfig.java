package pl.edu.wszib.bookstore.mapper;


import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.bookstore.mapper.ProductMapper;

@Configuration
public class ProductMapperConfig {

    @Bean
    public pl.edu.wszib.bookstore.mapper.ProductMapper productMapper(){
        return Mappers.getMapper(pl.edu.wszib.bookstore.mapper.ProductMapper.class);
    }
}
