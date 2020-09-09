package com.niit.carManifacture.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.carManifacture.Service.CarMakerService;
import com.niit.carManifacture.dao.CarMakerDao;
import com.niit.carManifacture.dao.CarModelDao;
import com.niit.carManifacture.model.CarMaker;

@Service
public class CarMakerServiceImpl implements CarMakerService {

	@Autowired
	CarMakerDao carMakerDao;
	@Autowired
	CarModelDao carModelDao;

	@Override
	public Long insertCarMaker(CarMaker carMaker) {
		return carMakerDao.insertCarMaker(carMaker);

	}

	@Override
	public CarMaker getCarMaker(Long id) {
		
		CarMaker carMaker = new CarMaker();
		carMaker = carMakerDao.searchCarMaker(id);
		return carMaker;
	}

	@Override
	public boolean deleteCarMaker(Long id) {
		// this function perform Deletion on child table

		if (carModelDao.parentCarMakerDataDeletion(id))
			return carMakerDao.deleteCar(id);
		else
			return carMakerDao.deleteCar(id);
	}

	@Override
	public List<CarMaker> viewAllCarMakers() {
		return carMakerDao.viewAllMakers();
	}

	@Override
	public boolean updateCarMaker(CarMaker carMaker) {
		return carMakerDao.updateCarMaker(carMaker);
	}

}
