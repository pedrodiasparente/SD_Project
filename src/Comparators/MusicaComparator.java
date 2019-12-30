package Comparators;

import MediaCenter.Musica;

import java.util.Comparator;

public class MusicaComparator implements Comparator<Musica> {
    @Override
    public int compare(Musica m1, Musica m2) {
        return m1.getIdentificador() - m2.getIdentificador();
    }
}
