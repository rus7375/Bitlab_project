package mini_project;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
   private String operationType;
   private ArrayList<Student> students;
   private Student student;

    public PackageData(String operationType, Student student) {
        this.operationType = operationType;
        this.student = student;
    }



    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
