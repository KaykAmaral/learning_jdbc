package src.model.dao.impl;

import src.db.DB;
import src.db.DbException;
import src.model.dao.DepartmentDao;
import src.model.entites.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection connection) { this.conn = connection; }

    @Override
    public void insert(Department department) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO department " +
                    "(Name) " +
                    "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, department.getName());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
            } else {
                throw new DbException("Department insert failed. No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE department " +
                    "SET Name = ? WHERE id = ?");

            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                return department;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                list.add(department);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
}