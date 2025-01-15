import java.io.*;
import java.util.*;

public class FileDataManager {

    public List<Student> createStudentsData(String filePath) {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] splituri = line.split(",");
                Integer id = Integer.valueOf(splituri[0]);
                String nume = splituri[1];
                String prenume = splituri[2];
                String grupa = splituri[3];
                Integer an = Integer.valueOf(splituri[4]);
                String username = splituri[5];
                String password = splituri[6];
                studenti.add(new Student(id, nume, prenume, grupa, an, username, password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studenti;
    }

    public List<Profesor> createProfesorData(String filePath) {
        List<Profesor> profesori = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] splituri = line.split(",");
                int id = Integer.parseInt(splituri[0]);
                String nume = splituri[1];
                String prenume = splituri[2];
                String username = splituri[3];
                String password = splituri[4];
                profesori.add(new Profesor(id, nume, prenume, username, password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profesori;
    }

    public List<Curs> createCoursesData(String cursFilePath, String noteFilePath) {
        List<Curs> cursuri = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cursFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splituri = line.split(",");
                int id = Integer.parseInt(splituri[0]);
                String nume = splituri[1];
                String descriere = splituri[2];
                int idProfesor = Integer.parseInt(splituri[3]);
                int an = Integer.parseInt(splituri[4]);
                cursuri.add(new Curs(id, nume, descriere, idProfesor,an));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(noteFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] splituri = line.split(",");
                int idCurs = Integer.parseInt(splituri[0]);
                int idStudent = Integer.parseInt(splituri[1]);

                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs) {
                        curs.adaugaStudent(idStudent);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cursuri;
    }
    public void createNoteData(String noteFilePath, List<Curs> cursuri) {
        try (BufferedReader br = new BufferedReader(new FileReader(noteFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] splituri = line.split(",");

                if (splituri.length != 3) {
                    System.err.println("Linie invalida in fisierul Note.txt: " + line);
                    continue;
                }

                int idCurs = Integer.parseInt(splituri[0]);
                int idStudent = Integer.parseInt(splituri[1]);
                String notaString = splituri[2];
                Integer nota = notaString.equalsIgnoreCase("N/A") ? -1 : Integer.parseInt(notaString);

                Curs cursGasit = null;
                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs) {
                        cursGasit = curs;
                        break;
                    }
                }

                if (cursGasit == null) {
                    continue;
                }

                cursGasit.adaugaStudent(idStudent);
                cursGasit.noteazaStudent(idStudent, nota);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

}

