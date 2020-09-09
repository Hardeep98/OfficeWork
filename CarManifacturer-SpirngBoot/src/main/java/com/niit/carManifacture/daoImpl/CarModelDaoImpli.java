package com.niit.carManifacture.daoImpl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.carManifacture.dao.CarModelDao;
import com.niit.carManifacture.model.CarModel;
import com.niit.carManifacture.repository.CarsModelRepository;

@Repository
public class CarModelDaoImpli implements CarModelDao {

	@Autowired
	Jdbi jdbi; 
	
	
	@Override
	public List<CarModel> viewAllModels() {
		CarsModelRepository repository= jdbi.onDemand(CarsModelRepository.class);
		return repository.viewAllModels();
	}

	
	@Override
	public Long addCar(String modelName, String yearOfManifacturer, Long carMakerId) {
		// TODO Auto-generated method stub
		CarsModelRepository repository= jdbi.onDemand(CarsModelRepository.class);
		return repository.insert(modelName, yearOfManifacturer, carMakerId);
		
	}

	@Override
	public boolean deleteCar(Long id) {
		CarsModelRepository repository= jdbi.onDemand(CarsModelRepository.class);
		return repository.deleteCarModel(id);
		
	}

	@Override
	public CarModel findCar(Long id) {
		CarsModelRepository repository = jdbi.onDemand(CarsModelRepository.class);
	
		return repository.viewCarModel(id);
	}


	@Override
	public boolean updateCar(Long carId, String modelName, String yearOfManifacturer, Long carMakerId) {
		CarsModelRepository repository = jdbi.onDemand(CarsModelRepository.class);
		return repository.updateCarModel(carId, modelName, yearOfManifacturer, carMakerId);
		
	}


	@Override
	public boolean parentCarMakerDataDeletion(Long carMakerId) {
		CarsModelRepository repository = jdbi.onDemand(CarsModelRepository.class);
		return repository.deleteParentCarMakerModel(carMakerId);
	}


}
