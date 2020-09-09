package com.niit.carManifacture.daoImpl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.carManifacture.dao.CarMakerDao;
import com.niit.carManifacture.model.CarMaker;
import com.niit.carManifacture.repository.CarMakerRepository;

@Repository
public class CarMakerDaoImpl implements CarMakerDao {

	/*
	 * DAOs they are used to perform or interact with Data base this is the Layer
	 * which are responsible for insertion deletion
	 */
	
	@Autowired
	Jdbi jdbi;

	@Override
	public List<CarMaker> viewAllMakers() {
		CarMakerRepository carMakerRepository = jdbi.onDemand(CarMakerRepository.class);
		return carMakerRepository.viewAllCarMakers();

	}

	// Adding a new Car Maker
	@Override
	public Long insertCarMaker(CarMaker carMaker) {
		CarMakerRepository carMakerRepository = jdbi.onDemand(CarMakerRepository.class);
		return carMakerRepository.insertCarMaker(carMaker);
	}

	@Override
	public CarMaker searchCarMaker(Long id) {
		CarMakerRepository carMakerRepository = jdbi.onDemand(CarMakerRepository.class);
		return carMakerRepository.searchCarMaker(id);
	}

	@Override
	public boolean deleteCar(Long id) {
		CarMakerRepository carMakerRepository = jdbi.onDemand(CarMakerRepository.class);
		return carMakerRepository.deleteCar(id);
	}

	@Override
	public boolean updateCarMaker(CarMaker carMaker) {
		CarMakerRepository carMakerRepository = jdbi.onDemand(CarMakerRepository.class);
		return carMakerRepository.updateCarMaker(carMaker);
	}

}
