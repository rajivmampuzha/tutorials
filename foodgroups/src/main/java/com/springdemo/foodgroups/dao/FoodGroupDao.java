package com.springdemo.foodgroups.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springdemo.foodgroups.model.FoodGroup;

public class FoodGroupDao {
	JdbcTemplate jdbcTemplate;
	
	private String selectQry = "SELECT id,name,description from foodgroups";
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List listFoodGroups(){
		return getJdbcTemplate().query(selectQry, new RowMapper() {
			
			@Override
			public FoodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodGroup gr = new FoodGroup();
				gr.setId(rs.getInt("id"));
				gr.setName(rs.getString("name"));
				gr.setDescription(rs.getString("description"));
				
				return gr;
			}
		});
	}

}
