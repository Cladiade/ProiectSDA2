import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileDataManager fileDataManager = new FileDataManager();
        FileDisplay fileDisplay = new FileDisplay();
        List<Student> studenti = fileDataManager.createStudentsData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Studenti.txt");
        List<Profesor> profesori = fileDataManager.createProfesorData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Profesori.txt");
        List<Curs> cursuri = fileDataManager.createCoursesData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Cursuri.txt", "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\src\\Note.txt");

        System.out.println("Studenti incarcati:");
        for (Student student : studenti) {
            System.out.println(student.getId() + " " + student.getNume() + " " + student.getPrenume());
        }

        System.out.println("Profesori incarcati:");
        for (Profesor profesor : profesori) {
            System.out.println(profesor.getId() + " " + profesor.getNume() + " " + profesor.getPrenume());
        }

        System.out.println("Cursuri incarcate:");
        for (Curs curs : cursuri) {
            System.out.println(curs.getId() + " " + curs.getNume() + " (Profesor ID: " + curs.getIdProfesor() + ")");
        }

        ManagerAutentificare auth = new ManagerAutentificare(studenti, profesori, fileDisplay,cursuri);
        Consola console = new Consola(auth,cursuri,studenti,profesori);
        console.start();
    }
}
