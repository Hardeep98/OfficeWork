package com.niit.carManifacture.repository;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.niit.carManifacture.model.CarMaker;

@Repository
public interface CarMakerRepository {

	@SqlQuery("SELECT id,name,brand FROM carMaker")
	@RegisterBeanMapper(CarMaker.class)
	public List<CarMaker> viewAllCarMakers();

	@SqlUpdate("INSERT INTO carmaker(id,name,brand) VALUES (:id, :name , :brand)")
	@GetGeneratedKeys
	public Long insertCarMaker(@BindBean CarMaker carMaker);

	@SqlBatch("INSERT INTO carmaker(id,name,brand) VALUES ( :id , :name , :brand )")
	@GetGeneratedKeys
	public List<Long> insertBulkCarMaker(@BindBean List<CarMaker> carMaker);

	@SqlQuery("SELECT id,name,brand from carmaker WHERE id = :id")
	@RegisterBeanMapper(CarMaker.class)
	public CarMaker searchCarMaker(@Bind("id") Long id);

	@SqlUpdate("DELETE FROM carmaker WHERE id = :id")
	public boolean deleteCar(@Bind("id") Long id);

	@SqlUpdate("UPDATE carmaker SET  name = :name , brand = :brand WHERE id = :id ")
	public boolean updateCarMaker(@BindBean CarMaker carMaker);
}
