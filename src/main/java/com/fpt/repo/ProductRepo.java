package com.fpt.repo;

import com.fpt.entity.Category;
import com.fpt.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("SELECT entity FROM Product entity WHERE category=:category")
	public List<Product> findByCategory(@Param("category") Category category, Pageable pageable);
	
	@Query("SELECT entity FROM Product entity WHERE status=:status")
	public List<Product> findByStatus(@Param("status") Integer status, Pageable pageable);

	@Query("select distinct a.name, sum(b.qty) from Product a inner join ProductDetail b on a.id =b.product.id group by a.name")
	public List<Object> getAllNameAndQuantity();

	@Query(value = "select distinct year(a.create_date) as years, sum(b.price*b.quantity) from orders a inner join order_details b " +
			"on a.id = b.order_id group by year(a.create_date) order by years ASC",nativeQuery = true)
	public List<Object> getTotalSalaryByYear();

	@Query(value = "select distinct month(a.create_date) as months ,year(a.create_date) as years, sum(b.price*b.quantity) from orders a inner join order_details b " +
			"on a.id = b.order_id group by months, years having years= ?1 order by months ASC",nativeQuery = true)
	public List<Object> getTotalSalaryByMonth(Integer year);

	@Query(value = "select distinct year(create_date) as years from orders order by years asc",nativeQuery = true)
	public List<Integer> getAllYears();
}
