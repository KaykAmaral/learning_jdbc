package src.application;

import src.model.dao.DaoFactory;
import src.model.dao.SellerDao;
import src.model.entites.Department;
import src.model.entites.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: seller findAll ===");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller obj : list2) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: seller insert ===");
        Seller seller1 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(seller1);
        System.out.println("Inserted seller successfully! New id = " + seller1.getId());

        System.out.println("\n=== TEST 5: seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Martha Beatriz");
        seller.setEmail("marthaBeatriz@gmail.com");
        sellerDao.update(seller);
        System.out.println("Updated seller successfully!");


        System.out.println("\n=== TEST 6: seller delete ===");
        sellerDao.deleteById(12);
        System.out.println("Delete completed successfully!");

    }

}