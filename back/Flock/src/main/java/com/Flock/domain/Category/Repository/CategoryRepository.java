package com.Flock.domain.Category.Repository;

import com.Flock.domain.Category.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
