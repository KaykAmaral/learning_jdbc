package src.application;

import src.model.dao.DaoFactory;
import src.model.dao.DepartmentDao;
import src.model.entites.Department;

import java.util.List;

public class Program2 {

    public static void main(String[] args) {
        // This Program was created for Department Class :)

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: Department findById ===");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println("\n=== TEST 2: Department findAll ===");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }

        System.out.println("\n=== TEST 3: Department insert ===");
        Department newDepartment = new Department(null, "Class");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New Id: " + newDepartment.getId());

        System.out.println("\n=== TEST 4: Department update  ===");
        Department dep2 = departmentDao.findById(1);
        dep2.setName("Books");
        departmentDao.update(dep2);
        System.out.println("Update Completed Successfully!");

        System.out.println("\n=== TEST 5: Department deleteById ===");
        departmentDao.deleteById(5);
        departmentDao.deleteById(9);
        System.out.println("Delete Completed Successfully!");

    }

}