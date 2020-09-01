package com.niit.carManifacturer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.niit.carManifacture.model.CarMaker;
import com.niit.carManifacture.model.CarModel;

public class CarModelMapper implements RowMapper<CarModel> {

	@Override
	public CarModel map(ResultSet rs, StatementContext ctx) throws SQLException {
		CarModel carModel = new CarModel();
		CarMaker carMaker= new CarMaker();
		
		carMaker.setId(rs.getLong("id"));
		carMaker.setBrand(rs.getString("brand"));
		carMaker.setName(rs.getString("name"));
		
		carModel.setId(rs.getLong("modelId"));
		carModel.setName(rs.getString("modelName"));
		carModel.setYear(rs.getLong("yearOfManifacture"));
		carModel.setManufacturer(carMaker);
		

		return carModel;
	}

}
