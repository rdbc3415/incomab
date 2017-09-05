/*
 * Developed by: Alexis Peralta Holyoak.
 */
package com.incomab.util;

/**
 *
 * @author peral
 */
public class ErroresUtil {
    private final String incorrectUser="VERIFIQUE EL USUARIO INGRESADO";
    private final String incorrectPassw="VERIFIQUE LA CONTRASEÑA";
    private final String missingConnection="SE PERDIO LA CONEXION A INTERNET";
    private final String cantLogin="DATOS DE SESION INCORRECTOS";
    
    
    
    /**
     * @return the incorrectUser
     */
    public String getIncorrectUser() {
        return incorrectUser;
    }

    /**
     * @return the incorrectPassw
     */
    public String getIncorrectPassw() {
        return incorrectPassw;
    }

    /**
     * @return the missingConnection
     */
    public String getMissingConnection() {
        return missingConnection;
    }

    /**
     * @return the cantLogin
     */
    public String getCantLogin() {
        return cantLogin;
    }
    
    
    
    
    
}
