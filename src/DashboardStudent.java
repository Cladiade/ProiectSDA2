import java.util.*;
import java.util.stream.Collectors;

public class DashboardStudent {
    private List<Curs> cursuri;
    private int idStudent;

    public DashboardStudent(List<Curs> cursuri, int idStudent) {
        this.cursuri = cursuri;
        this.idStudent = idStudent;
    }

    public void vizualizareCursuri(int an) {
        List<Curs> cursuriAn = cursuri.stream()
                .filter(curs -> curs.getAn() == an)
                .collect(Collectors.toList());

        System.out.println("Cursurile din anul " + an + ":");
        for (Curs curs : cursuriAn) {
            System.out.println(curs.getNume() + " (ID: " + curs.getId() + ")");
        }
    }

    public void vizualizareNote() {
        System.out.println("Note cursuri:");
        for (Curs curs : cursuri) {
            Integer nota = curs.getNote().get(idStudent);
            String notaText = (nota == null || nota == -1) ? " " : String.valueOf(nota);
            System.out.println(curs.getNume() + ": " + notaText);
        }
    }

    public void calculareMedie() {
        double suma = 0;
        int numarCursuri = 0;

        for (Curs curs : cursuri) {
            Integer nota = curs.getNote().get(idStudent);
            if (nota != null && nota > -1) {
                suma += nota;
                numarCursuri++;
            }
        }

        if (numarCursuri > 0) {
            double media = suma / numarCursuri;
            System.out.printf("Media pentru cursurile cu note este: ", media);
        } else {
            System.out.println("Nu exista note suficiente pentru calcularea mediei");
        }
    }

    public void identificareRestante() {
        System.out.println("Materii cu restante: ");
        boolean areRestante = false;

        for (Curs curs : cursuri) {
            Integer nota = curs.getNote().get(idStudent);
            if (nota != null && nota < 5 && nota > -1) {
                System.out.println( curs.getNume());
                areRestante = true;
            }
        }

        if (!areRestante) {
            System.out.println("Nu sunt restante");
        }
    }
}
