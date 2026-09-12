package iuh.fit.reviewpracticeservlet.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/12/2026
 */

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JpaUtil.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JpaUtil.destroy();
    }
}
