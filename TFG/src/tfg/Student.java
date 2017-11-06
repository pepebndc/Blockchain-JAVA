/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        if (id != student.id) {
            return false;
        }
        if (name != null ? !name.equals(student.name) : student.name != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return id;
    }

    public String toString() {
        return "Id = " + getId() + " ; Name = " + getName();
    }
}
