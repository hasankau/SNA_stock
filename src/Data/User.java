/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author HASANKA
 */
public class User {
    final public static int STAFF = 0;
    final public static int ADMIN = 1;
    private String UserName;
    private int UserRole;
    private int id;

    /**
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the UserRole
     */
    public int getUserRole() {
        return UserRole;
    }

    /**
     * @param UserRole the UserRole to set
     */
    public void setUserRole(int UserRole) {
        this.UserRole = UserRole;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
