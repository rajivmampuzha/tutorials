package com.springdemo.foodgroups.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.springdemo.foodgroups.model.FoodGroup;

public class FoodGroupDao {
	private final class FoodGroupCreator implements RowMapper<FoodGroup> {
		@Override
		public FoodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			FoodGroup gr = new FoodGroup();
			gr.setId(rs.getInt("id"));
			gr.setName(rs.getString("name"));
			gr.setDescription(rs.getString("description"));

			return gr;
		}
	}

	NamedParameterJdbcTemplate jdbcTemplate;

	private String selectQry = "SELECT id,name,description from foodgroup";
	private String selectQryById = "SELECT id,name,description from foodgroups where id=:id";
	private String insert = "INSERT INTO foodgroups(name,description)VALUES(:name,:description)";
	private String update = "UPDATE foodgroups SET name=:name,description=:description where id=:id";
	private String delete = "DELETE from foodgroups where id=:id";


	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<FoodGroup> listFoodGroups() {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("groupName", "Vegetables");
		return getJdbcTemplate().query(selectQry, map, new FoodGroupCreator());
	}

	public FoodGroup findFoodGroupById(int id) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);

		return getJdbcTemplate().queryForObject(selectQryById, paramMap, new FoodGroupCreator());

	}

	public boolean addFoodGroup(String name, String description) {
		boolean resp = false;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", name);
		paramMap.addValue("description", description);

		int numberofRecordsEffected = getJdbcTemplate().update(insert, paramMap);
		if (numberofRecordsEffected > 0) {
			resp = true;
		}
		return resp;
	}

	public boolean create(FoodGroup fg) {
		boolean resp = false;
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(fg);

		int numberofRecordsEffected = getJdbcTemplate().update(insert, paramMap);
		if (numberofRecordsEffected > 0) {
			resp = true;
		}
		return resp;
	}
	
	public boolean update(FoodGroup fg) {
		boolean resp = false;
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(fg);

		int numberofRecordsEffected = getJdbcTemplate().update(update, paramMap);
		if (numberofRecordsEffected > 0) {
			resp = true;
		}
		return resp;
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		boolean resp = false;

		int numberofRecordsEffected = getJdbcTemplate().update(delete, paramMap);
		if (numberofRecordsEffected > 0) {
			resp = true;
		}
		return resp;
	}

	public int[] createFoodGroups(List<FoodGroup> foodGroups) {

		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(foodGroups);
		int[] numberofeffectedRecords = getJdbcTemplate().batchUpdate(insert, batchArgs);
		return numberofeffectedRecords;
	}

}
