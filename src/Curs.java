import java.util.*;

public class Curs {
    private int id;
    private String nume;
    private String descriere;
    private int idProfesor;
    private int an;
    private Map<Integer, Integer> note;

    public Curs(int id, String nume, String descriere, int idProfesor, int an) {
        this.id = id;
        this.nume = nume;
        this.descriere = descriere;
        this.idProfesor = idProfesor;
        this.an = an;
        this.note = new HashMap<>();
    }

   synchronized public int getId() {
        return id;
    }

    synchronized public String getNume() {
        return nume;
    }

    synchronized public int getAn() {
        return an;
    }

    synchronized public Map<Integer, Integer> getNote() {
        return note;
    }

    synchronized public void adaugaStudent(int idStudent) {
        note.put(idStudent, -1);
    }

    synchronized public void adaugaStudent(int idStudent, int nota) {
        note.put(idStudent, nota);
    }

    synchronized public void noteazaStudent(int idStudent, int nota) {
        if (note.containsKey(idStudent)) {
            note.put(idStudent, nota);
        } else {
            System.out.println("Studentul nu este inscris la acest curs");
        }
    }

    synchronized public String getDescriere() {
        return descriere;
    }

    synchronized public int getIdProfesor() {
        return idProfesor;
    }

    synchronized public List<Integer> getIdStudenti() {
        return new ArrayList<>(note.keySet());
    }
}
