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

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public int getAn() {
        return an;
    }

    public Map<Integer, Integer> getNote() {
        return note;
    }

    public void adaugaStudent(int idStudent) {
        note.put(idStudent, -1);
    }

    public void noteazaStudent(int idStudent, int nota) {
        if (note.containsKey(idStudent)) {
            note.put(idStudent, nota);
        } else {
            System.out.println("Studentul nu este inscris la acest curs");
        }
    }

    public String getDescriere() {
        return descriere;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public List<Integer> getIdStudenti() {
        return new ArrayList<>(note.keySet());
    }
}
