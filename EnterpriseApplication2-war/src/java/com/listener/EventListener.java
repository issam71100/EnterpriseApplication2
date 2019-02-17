/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author issam
 */
public class EventListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EnterpriseApplication2-ejbPU");
        e.getServletContext().setAttribute("emf", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf = (EntityManagerFactory) e.getServletContext().getAttribute("emf");
        emf.close();
    }
}
