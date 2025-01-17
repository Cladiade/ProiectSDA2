import java.util.List;
import java.util.Scanner;

public class Consola {
    private ManagerAutentificare auth;
    private List<Curs> cursuri;
    private List<Student> studenti;
    private List<Profesor> profesori;

    public Consola(ManagerAutentificare auth, List<Curs> cursuri, List<Student> studenti, List<Profesor> profesori) {
        this.auth = auth;
        this.cursuri = cursuri;
        this.studenti = studenti;
        this.profesori = profesori;
    }

   synchronized public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Selectati optiunea dorita:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Close");
            int optiune = scanner.nextInt();
            scanner.nextLine();
            switch (optiune) {
                case 1:
                    System.out.println("Introduceti username:");
                    String username = scanner.nextLine();
                    System.out.println("Introduceti parola:");
                    String password = scanner.nextLine();
                    Object user = auth.login(username, password);
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        System.out.println("Autentificare reusita ca Student");
                        DashboardStudent(scanner, student);
                    } else if (user instanceof Profesor) {
                        Profesor profesor = (Profesor) user;
                        System.out.println("Autentificare reusita ca Profesor");
                        DashboardProfesor(scanner, profesor);
                    } else {
                        System.out.println("Autentificare esuata, verificati credentialele");
                    }
                    break;
                case 2:
                    System.out.println("1. Inregistrare Student");
                    System.out.println("2. Inregistrare Profesor");
                    int tip = scanner.nextInt();
                    scanner.nextLine();

                    if (tip == 1) {
                        System.out.println("Introduceti numele:");
                        String nume = scanner.nextLine();
                        System.out.println("Introduceti prenumele:");
                        String prenume = scanner.nextLine();
                        System.out.println("Introduceti grupa:");
                        String grupa = scanner.nextLine();
                        System.out.println("Introduceti anul:");
                        int an = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduceti username:");
                        String username1 = scanner.nextLine();
                        System.out.println("Introduceti parola:");
                        String password1 = scanner.nextLine();

                        if (auth.registerStudent(nume, prenume, grupa, an, username1, password1, "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Studenti.txt")) {
                            System.out.println("Inregistrare reusita");
                        } else {
                            System.out.println("Eroare: Username-ul exista deja");
                        }
                    } else if (tip == 2) {
                        System.out.println("Introduceti numele:");
                        String nume = scanner.nextLine();
                        System.out.println("Introduceti prenumele:");
                        String prenume = scanner.nextLine();
                        System.out.println("Introduceti username:");
                        String username2 = scanner.nextLine();
                        System.out.println("Introduceti parola:");
                        String password2 = scanner.nextLine();

                        if (auth.registerProfesor(nume, prenume, username2, password2, "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Profesori.txt")) {
                            System.out.println("Inregistrare reusita");
                        } else {
                            System.out.println("Eroare: Username-ul exista deja");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Aplicatia a fost inchisa");
                    return;

                default:
                    System.out.println("Optiune invalida");
                    break;
            }
        }
    }

    private void DashboardStudent(Scanner scanner, Student student) {
        DashboardStudent dashboard = new DashboardStudent(cursuri, student.getId());
        while (true) {
            System.out.println("Dashboard Student:");
            System.out.println("1. Vizualizeaza cursuri dintr-un an");
            System.out.println("2. Vizualizeaza notele");
            System.out.println("3. Calculeaza media");
            System.out.println("4. Identifica restante");
            System.out.println("5. Logout");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.println("Introduceti anul");
                    int an = scanner.nextInt();
                    scanner.nextLine();
                    dashboard.vizualizareCursuri(an);
                    break;
                case 2:
                    dashboard.vizualizareNote();
                    break;
                case 3:
                    dashboard.calculareMedie();
                    break;
                case 4:
                    dashboard.identificareRestante();
                    break;
                case 5:
                    System.out.println("Ati fost delogat");
                    return;
                default:
                    System.out.println("Optiune invalida");
                    break;
            }
        }
    }

    private void DashboardProfesor(Scanner scanner, Profesor profesor) {
        DashboardProfesor dashboard = new DashboardProfesor(cursuri,studenti, profesor.getId(), new FileDisplay(), "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Cursuri.txt", "C:\\Users\\Gabi\\Desktop\\Teme Facultate\\Teme SDA\\Proiect SDA\\ProiectSDA2\\src\\Note.txt");

        while (true) {
            System.out.println("Dashboard Profesor:");
            System.out.println("1. Afiseaza lista cursurilor predate");
            System.out.println("2. Afiseaza studentii inscrisi la un curs");
            System.out.println("3. Noteaza un student");
            System.out.println("4. Logout");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    dashboard.afisareCursuri();
                    break;
                case 2:
                    System.out.println("Introduceti ID-ul cursului:");
                    int idCurs = scanner.nextInt();
                    scanner.nextLine();
                    dashboard.afisareStudenti(idCurs);
                    break;
                case 3:
                    System.out.println("Introduceti ID-ul cursului:");
                    int idCursNota = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introduceti ID-ul studentului:");
                    int idStudent = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introduceti nota:");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    dashboard.noteazaStudent(idCursNota, idStudent, nota);
                    break;
                case 4:
                    System.out.println("Ati fost delogat");
                    return;
                default:
                    System.out.println("Optiune invalida");
                    break;
            }
        }
    }

}
