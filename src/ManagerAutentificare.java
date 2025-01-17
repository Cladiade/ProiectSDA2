import java.util.List;

public class ManagerAutentificare {
    private List<Student> studenti;
    private List<Profesor> profesori;
    private FileDisplay fileDisplay;
    private List<Curs> cursuri;

    public ManagerAutentificare(List<Student> studenti, List<Profesor> profesori, FileDisplay fileDisplay, List<Curs> cursuri) {
        this.studenti = studenti;
        this.profesori = profesori;
        this.fileDisplay = fileDisplay;
        this.cursuri = cursuri;
    }

    public Object login(String username, String password) {
        for (Student student : studenti) {
            if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }

        for (Profesor profesor : profesori) {
            if (profesor.getUsername().equals(username) && profesor.getPassword().equals(password)) {
                return profesor;
            }
        }

        return null; // Login eșuat
    }

    public boolean registerStudent(String nume, String prenume, String grupa, int an, String username, String password, String studentFilePath) {
        // Verificăm unicitatea username-ului
        for (Student student : studenti) {
            if (student.getUsername().equals(username)) {
                return false; // Username-ul există deja
            }
        }

        // Adăugăm studentul nou
        int id = studenti.size() + 1; // Generăm ID-ul
        Student nouStudent = new Student(id, nume, prenume, grupa, an, username, password);
        studenti.add(nouStudent);
        for(Curs curs : cursuri)
            if(curs.getAn() == an)
                curs.adaugaStudent(id);
        // Salvăm lista actualizată în fișier
        fileDisplay.displayStudents(studenti, studentFilePath);
        fileDisplay.displayCourses(cursuri, "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Cursuri.txt","C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Note.txt");
        return true; // Înregistrarea a avut succes
    }

    public boolean registerProfesor(String nume, String prenume, String username, String password, String profesorFilePath) {
        // Verificăm unicitatea username-ului
        for (Profesor profesor : profesori) {
            if (profesor.getUsername().equals(username)) {
                return false; // Username-ul există deja
            }
        }

        // Adăugăm profesorul nou
        int id = profesori.size() + 1; // Generăm ID-ul
        Profesor nouProfesor = new Profesor(id, nume, prenume, username, password);
        profesori.add(nouProfesor);

        // Salvăm lista actualizată în fișier
        fileDisplay.displayTeachers(profesori, profesorFilePath);

        return true; // Înregistrarea a avut succes
    }
}

