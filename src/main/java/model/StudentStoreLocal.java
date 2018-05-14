package model;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentStoreLocal implements StudentStore {

    private Map<Integer, Student> studentsMap = null;

    private Map<Integer, Student> getStudentsMap() {
        if (studentsMap == null) {
            try {
                File file = new File("students.dat");
                if (!file.exists()) {
                    Student stud = new Student(1, "example", "group");
                    studentsMap = new HashMap<>();
                    studentsMap.put(stud.getId(), stud);
                    saveStudentsMap();
                } else {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    studentsMap = (Map<Integer, Student>) ois.readObject();
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return studentsMap;
    }

    private void saveStudentsMap() {
        try {
            File file = new File("students.dat");
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(studentsMap);
            oos.close();
            System.out.println(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Student> getStudents() {
        return getStudentsMap().values();
    }

    public Student getStudent(int id) {
        return getStudentsMap().get(id);
    }

    public int addStudent(Student student) {
        if (getStudentsMap().containsKey(student.getId()))
            return 0;
        studentsMap.put(student.getId(), student);
        saveStudentsMap();
        return 1;
    }

    public int deleteStudent(int id) {
        if (!getStudentsMap().containsKey(id))
            return 0;
        studentsMap.remove(id);
        saveStudentsMap();
        return 1;
    }

    public int updateStudent(Student stud) {
        if (!getStudentsMap().containsKey(stud.getId()))
            return 0;
        studentsMap.put(stud.getId(), stud);
        saveStudentsMap();
        return 1;
    }

}
