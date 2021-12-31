package com.fpt.repo;

import com.fpt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

	@Query("SELECT entity FROM Category entity WHERE name=:name")
	public Category findByName(@Param("name") String name);
	
	@Query("SELECT entity FROM Category entity WHERE status=:status")
	public Category findByStatus(@Param("status") Integer status);

	@Query("select c from Category c where c.supCategory.id = ?1")
	List<Category> findAllBySupCategoryId(Integer id);
	
}
