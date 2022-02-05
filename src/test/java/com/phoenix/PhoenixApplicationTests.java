package com.phoenix;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class PhoenixApplicationTests {

	@Value("${test.property.name}")
	private String testName;

	@Autowired
	private DataSource dataSource;

//	@Test
//	void contextLoads() {
//	}

	@Test
	void valueExits(){
		assertThat(testName).isEqualTo("phoenix");
		log.info(testName);

	}
	@Test
	void applicationCanConnectToDatabaseTest(){

		Connection connection;
		try{connection = dataSource.getConnection();
			assertThat(connection).isNotNull();
			log.info("Connection -> {}", connection.getSchema());
		}catch (SQLException e){
			e.printStackTrace();
		}

	}

}
