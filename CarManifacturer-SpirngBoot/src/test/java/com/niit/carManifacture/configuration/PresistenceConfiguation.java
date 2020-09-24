package com.niit.carManifacture.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@EnableAutoConfiguration
@Configuration
public class PresistenceConfiguation {

	@Bean
	public Jdbi jdbi(DataSource dataSource) {
		TransactionAwareDataSourceProxy awareDataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
		Jdbi jdbi = Jdbi.create(awareDataSourceProxy).installPlugin(new H2DatabasePlugin());
		jdbi.installPlugin(new SqlObjectPlugin());
		try {
			dbcreate(jdbi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jdbi;
	}

	void dbcreate(Jdbi jdbi) throws IOException {
//		File file = ResourceUtils.getFile("classpath:schema.sql");
		Handle handle = jdbi.open();

		handle.execute(
				"CREATE TABLE  IF NOT EXISTS carMaker(id IDENTITY NOT NULL PRIMARY KEY  ,name varchar(50), brand varchar(50))");
		handle.execute("INSERT INTO carMaker(id,name, brand ) values (?,?,?)", 1, "Hardeep singh", "BMW");
		handle.execute("INSERT INTO carMaker(id,name, brand ) values (?,?,?)", 2, "Jashandeep singh", "Audi");
		handle.execute("INSERT INTO carMaker(id,name, brand ) values (?,?,?)", 3, "Amrinder singh", "Mercedes");
		handle.execute("INSERT INTO carMaker(id,name, brand ) values (?,?,?)", 4, "Amandeep", "Tesla");
		handle.execute(
				"CREATE TABLE  IF NOT EXISTS carModel( model_id INT GENERATED ALWAYS AS IDENTITY, model_name VARCHAR(50) Not NULL, year_of_manifacture varchar(50), car_maker_id INT, PRIMARY KEY(model_id), CONSTRAINT fk_customer FOREIGN KEY(car_maker_id) REFERENCES carmaker(id));");
		handle.execute("INSERT INTO carModel(model_name , year_of_manifacture , car_maker_id ) values (?,?,?)", "BMW",
				"2020", 1);
		handle.execute("INSERT INTO carModel(model_name , year_of_manifacture , car_maker_id ) values (?,?,?)", "R8",
				"2020", 2);
		handle.execute("INSERT INTO carModel(model_name , year_of_manifacture , car_maker_id ) values (?,?,?)",
				"Mercedes", "2019", 3);
		handle.execute("INSERT INTO carModel(model_name , year_of_manifacture , car_maker_id ) values (?,?,?)", "PPs",
				"2019", 3);

////		String tableSql = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
////		String datasql = new String(
////				Files.readAllBytes(Paths.get(ResourceUtils.getFile("classpath:data.sql").getAbsolutePath())));
//		handle.createScript(tableSql).execute();
//		handle.createScript(datasql).execute();
		handle.commit();
		handle.close();

	}

}
