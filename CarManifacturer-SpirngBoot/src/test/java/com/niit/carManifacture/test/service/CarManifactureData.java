package com.niit.carManifacture.test.service;

import com.niit.carManifacture.model.CarMaker;
import com.niit.carManifacture.model.CarModel;

public class CarManifactureData {

	
	
	public static CarModel getCarModelData1() {
		CarModel carModel1 = new CarModel();
		carModel1.setId(1L);
		carModel1.setName("R8");
		carModel1.setYear(1996L);
		carModel1.setManufacturer(getCarMakerData());
		return carModel1;

	}

	public static CarModel getCarModelData2() {
		CarModel carModel1 = new CarModel();
		carModel1.setId(2L);
		carModel1.setName("q5");
		carModel1.setYear(2000L);
		carModel1.setManufacturer(getCarMakerData());
		return carModel1;

	}
	public static CarModel getCarModelDataWithErrorInForiegnKey() {
		CarModel carModel1 = new CarModel();
		carModel1.setId(2L);
		carModel1.setName("Mercedes");
		carModel1.setYear(2000L);
		carModel1.setManufacturer(getCarMakerDataWithoutID());
		return carModel1;

	}
	
	public static CarModel actualDataUpdate() {
		CarModel carModel1 = new CarModel();
		carModel1.setId(21L);
		carModel1.setName("Mercedes");
		carModel1.setYear(2020L);
		carModel1.setManufacturer( actualCarMakerData());
		return carModel1;
		
		
	}
	public static CarMaker actualCarMakerData() {
		CarMaker carMaker1 = new CarMaker();
		carMaker1.setBrand("BMW");
		carMaker1.setId(3L);
		carMaker1.setName("DeepDahion");
		return carMaker1;
	}
	
	public static CarMaker getCarMakerData() {
		CarMaker carMaker1 = new CarMaker();
		carMaker1.setBrand("BMW");
		carMaker1.setId(12L);
		carMaker1.setName("DeepDahion");
		return carMaker1;

	}
	
	public static CarMaker getCarMakerData2() {
		CarMaker carMaker1 = new CarMaker();
		carMaker1.setBrand("Lamborgini");
		carMaker1.setId(2L);
		carMaker1.setName("Inder Nagpal");
		return carMaker1;

	}
	
	public static CarMaker getCarMakerDataWithoutID() {
		CarMaker carMaker1 = new CarMaker();
		carMaker1.setBrand("Camaro");
		carMaker1.setId(15L);
		carMaker1.setName("Inder Naggu");
		return carMaker1;

	}

	
}
