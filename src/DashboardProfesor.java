import java.sql.SQLOutput;
import java.util.List;

public class DashboardProfesor {
    private List<Curs> cursuri;
    private int idProfesor;
    private FileDisplay fileDisplay;
    private String notePath;
    private String cursuriPath;
    private List<Student> studenti;

    public DashboardProfesor(List<Curs> cursuri,List<Student> studenti, int idProfesor, FileDisplay fileDisplay, String cursuriPath, String notePath) {
        this.cursuri = cursuri;
        this.idProfesor = idProfesor;
        this.fileDisplay = fileDisplay;
        this.notePath = notePath;
        this.cursuriPath = cursuriPath;
        this.studenti = studenti;
    }

   synchronized public void afisareCursuri() {
        System.out.println("Cursurile predate:");
        for (Curs curs : cursuri) {
            if (curs.getIdProfesor() == idProfesor) {
                System.out.println( curs.getNume() + " (ID: " + curs.getId() + ")");
            }
        }
    }

    synchronized  public void afisareStudenti(int idCurs) {
        Curs cursGasit = null;
        for (Curs curs : cursuri) {
            if (curs.getId() == idCurs && curs.getIdProfesor() == idProfesor) {
                cursGasit = curs;
                break;
            }
        }

        if (cursGasit == null) {
            System.out.println("Cursul cu ID-ul " + idCurs + " nu exista sau nu apartine acestui profesor");
            return;
        }

        System.out.println("Studentii inscrisi la cursul " + cursGasit.getNume() + ":");
        for (Integer idStudent : cursGasit.getIdStudenti()) {
            Student stud = null;
            for (Student student : studenti) {
                if(student.getId() == idStudent)  {
                    stud = student;
                    break;
                }
            }
            if(stud != null) {
            System.out.println("ID Student: " + stud.getId() + " " + stud.getNume() + " " + stud.getPrenume());
            }
            else System.out.println("ID Student " + idStudent + " nu exista");
        }
    }

    synchronized  public void noteazaStudent(int idCurs, int idStudent, int nota) {
        Curs cursGasit = null;
        for (Curs curs : cursuri) {
            if (curs.getId() == idCurs && curs.getIdProfesor() == idProfesor) {
                cursGasit = curs;
                break;
            }
        }

        if (cursGasit == null) {
            System.out.println("Cursul cu ID-ul " + idCurs + " nu exista sau nu apartine acestui profesor");
            return;
        }

        if (!cursGasit.getNote().containsKey(idStudent)) {
            System.out.println("Studentul cu ID-ul " + idStudent + " nu este inscris la cursul " + cursGasit.getNume());
            return;
        }

        cursGasit.noteazaStudent(idStudent, nota);
        System.out.println("Studentul cu ID-ul " + idStudent + " a fost notat cu " + nota + " la cursul " + cursGasit.getNume());

        fileDisplay.displayNote(cursuri, notePath);
        System.out.println("Modificarile au fost salvate");
    }
}
