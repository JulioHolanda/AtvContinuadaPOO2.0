package br.gov.cesarschool.poo.fidelidade.util;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Comparavel;

public class Ordenador {
    public static <T extends Comparavel> void ordenar(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int k = i + 1; k < array.length; k++) {
                if (array[i].comparar(array[k]) > 0) {
                    T aux = array[i];
                    array[i] = array[k];
                    array[k] = aux;
                }
            }
        }
    }
}
