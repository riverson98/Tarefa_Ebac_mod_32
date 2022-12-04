package org.rcosta.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_PRODUTO")
public class Product{
        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="product_seq")
        @SequenceGenerator(name="product_seq", sequenceName="sq_product", initialValue = 1, allocationSize = 1)
        private Long id;
        @Column(name = "NOME", length = 50, nullable = false)
        private String name;
        @Column(name = "PREÇO", nullable = false)
        private Double price;

        public Product() {
        }

        public Product(String name, Double price) {
                this.name = name;
                this.price = price;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }
}

