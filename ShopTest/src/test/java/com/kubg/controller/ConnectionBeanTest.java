package com.kubg.controller;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"file:src/main/webapp/Web-INF/spring/root-context.xml"})
public class ConnectionBeanTest {
	DataSource dataSource;
	@Test
	public void test() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
