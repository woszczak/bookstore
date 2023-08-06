package pl.edu.wszib.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

       @Transient
    private Double totalPrice;

    @Transient
    private int itemsNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CartItem> items;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;

//    private String tokenSession;


}
