package com.Flock.domain.Category.Entity;

import com.Flock.domain.Club.Entity.Club;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    private String categoryName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
}
