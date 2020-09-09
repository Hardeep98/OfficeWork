package com.niit.carManifacture.repository;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.niit.carManifacture.model.CarModel;
import com.niit.carManifacturer.mapper.CarModelMapper;

public interface CarsModelRepository {

	// Query with inner Join
	@SqlQuery("SELECT cmo.model_id AS modelId , cmo.model_name  AS modelName,cmo.year_of_manifacture AS yearOfManifacture,"
			+ "cmo.car_maker_id AS makerID, cma.id AS id, cma.name AS name ,cma.brand AS brand "
			+ "FROM carmodel cmo , carmaker cma WHERE cma.id =cmo.car_maker_id")
	@RegisterRowMapper(CarModelMapper.class)
	public List<CarModel> viewAllModels();

	@SqlUpdate("INSERT INTO carmodel(model_name,year_of_manifacture,car_maker_id) VALUES(:modelName , :yearOfManifacture , :carMakerID)")
	@GetGeneratedKeys
	public Long insert(@Bind("modelName") String modelName, @Bind("yearOfManifacture") String yearOfManifacturer,
			@Bind("carMakerID") Long carMakerId);

	@SqlQuery("SELECT cmo.model_id AS modelId , cmo.model_name AS modelName,cmo.year_of_manifacture AS yearOfManifacture,cmo."
			+ "car_maker_id AS makerID,cma.id AS id, cma.name AS name ,cma.brand AS brand from carmodel cmo , "
			+ "carmaker cma where cmo.car_maker_id = cma.ID AND cmo.model_id = :modelId ")
	@RegisterRowMapper(CarModelMapper.class)
	public CarModel viewCarModel(@Bind("modelId") Long modelId);

	@SqlUpdate("DELETE FROM carmodel WHERE model_id = :modelId")
	public boolean deleteCarModel(@Bind("modelId") Long modelId);

	@SqlUpdate("UPDATE carmodel SET model_name = :modelName,year_of_manifacture = :yearOfManifacture,car_maker_id = :carMakerId WHERE model_id = :model_id")
	public boolean updateCarModel(@Bind("model_id") Long id, @Bind("modelName") String modelName,
			@Bind("yearOfManifacture") String yearOfManifacturer, @Bind("carMakerId") Long carMakerId);

	// if the CarMaker who made the car will delete then this data Will also be
	// deleted
	@SqlUpdate("DELETE FROM carmodel WHERE car_maker_id = :carMakerId")
	public boolean deleteParentCarMakerModel(@Bind("carMakerId") Long carMakerId);

}
