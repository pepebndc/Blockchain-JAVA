/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class BlockChain {

    private String nombre;
    private int dificultad;
    private List<Bloque> cadena;

    BlockChain(String nombre) {
        this.nombre = nombre;
        this.dificultad = 0;
        cadena = new ArrayList<>();

    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cadena
     */
    public List<Bloque> getCadena() {
        return cadena;
    }

    /**
     * @param cadena the cadena to set
     */
    public void setCadena(List<Bloque> cadena) {
        this.cadena = cadena;
    }

    public boolean cadenaValida() throws NoSuchAlgorithmException {
        String[] hashes = new String[this.getCadena().size()];
        int longitud = this.getCadena().size();

        Iterator<Bloque> iterator = this.getCadena().iterator();

        while (iterator.hasNext()) {

            Bloque b = iterator.next();

            String hash = b.getHash();
            int index = b.getIndex();
            String hashAnteriorBloque = b.getHashAnterior();
            String time = b.getTiempo();
            String data = b.getData();
            int relleno = b.getRelleno();

            hashes[index] = hash;
            String hashRecalculado = Bloque.calcularHash(index, time, data, hashAnteriorBloque, relleno);

            if (index != 0) {
                if (index != 1) {
                    if (index < (longitud - 1)) {
                        if (!hash.equals(hashRecalculado)) {
                            System.out.println("diferente hash calculado " + index);
                            return false;
                        }

                        if (!hashAnteriorBloque.equals(hashes[(index - 1)])) {
                            System.out.println("diferente hash anterior " + index);
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }

    /**
     * @return the dificultad
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

}
