package modelo;

public class Cliente extends Persona {

    public Cliente() {
    }

    public Cliente(int id, String nombre,
                   String telefono,
                   String email,
                   String direccion) {

        super(id,nombre,telefono,email,direccion);

    }

    @Override
    public String toString() {

        return nombre;

    }

}