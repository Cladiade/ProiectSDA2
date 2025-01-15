import java.io.*;
import java.util.List;

public class FileDisplay {

    public void displayStudents(List<Student> students, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                bw.write(student.getId() + "," +
                        student.getNume() + "," +
                        student.getPrenume() + "," +
                        student.getGrupa() + "," +
                        student.getAn() + "," +
                        student.getUsername() + "," +
                        student.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTeachers(List<Profesor> profesors, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Profesor profesor : profesors) {
                bw.write(profesor.getId() + "," +
                        profesor.getNume() + "," +
                        profesor.getPrenume() + "," +
                        profesor.getUsername() + "," +
                        profesor.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayCourses(List<Curs> cursuri, String cursFilePath, String noteFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cursFilePath))) {
            for (Curs curs : cursuri) {
                bw.write(curs.getId() + "," +
                        curs.getNume() + "," +
                        curs.getDescriere() + "," +
                        curs.getIdProfesor());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(noteFilePath))) {
            for (Curs curs : cursuri) {
                for (int idStudent : curs.getIdStudenti()) {
                    bw.write(curs.getId() + "," + idStudent + ",null");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displayNote(List<Curs> cursuri, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Curs curs : cursuri) {
                for (Integer idStudent : curs.getIdStudenti()) {
                    Integer nota = curs.getNote().get(idStudent);
                    String notaText = (nota == null || nota == -1) ? "N/A" : nota.toString();
                    bw.write(curs.getId() + "," + idStudent + "," + notaText);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

