package iuh.fit.reviewpracticeservlet.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/12/2026
 */
public class JpaUtil {

    @Getter
    private static EntityManagerFactory emf;

    public static void init(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("default");
        }
    }

    public static void destroy(){
        if (emf.isOpen()) emf.close();
    }
}
