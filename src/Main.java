import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileDataManager fileDataManager = new FileDataManager();
        FileDisplay fileDisplay = new FileDisplay();
        List<Student> studenti = fileDataManager.createStudentsData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Studenti.txt");
        List<Profesor> profesori = fileDataManager.createProfesorData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Profesori.txt");
        List<Curs> cursuri = fileDataManager.createCoursesData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Cursuri.txt", "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Note.txt");

class thread implements Runnable {
    Thread t;
    List<Student> studenti;
    List<Profesor> profesori;
    List<Curs> cursuri;
    public thread(List<Student> studenti, List<Profesor> profesori, List<Curs> cursuri) {
        t= new Thread(this);
        this.studenti = studenti;
        this.profesori = profesori;
        this.cursuri = cursuri;
        t.start();
    }
    synchronized public void run() {
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
    }
}
       class thread2 implements Runnable {
    Thread t;
    Consola console;
    thread2(Consola console) {
        this.console = console;
        t = new Thread(this);
        t.start();
    }
    synchronized public void run() {
        console.start();
    }
       }

        fileDataManager.createNoteData("C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Note.txt",cursuri);
        fileDisplay.displayNote(cursuri,"C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Note.txt");

        ManagerAutentificare auth = new ManagerAutentificare(studenti, profesori, fileDisplay,cursuri);
        Consola console = new Consola(auth,cursuri,studenti,profesori);
        thread t1 = new thread(studenti,profesori,cursuri);
        thread2 t2 = new thread2(console);
        try{
            t1.t.join();
            t2.t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
