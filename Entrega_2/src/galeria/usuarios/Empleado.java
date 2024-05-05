package galeria.usuarios;

public abstract class Empleado {
    protected String idEmpleado;
    protected String nombre;
    protected String username;
    protected String passwordHash;
    protected String role;  

    public Empleado(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;  
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
