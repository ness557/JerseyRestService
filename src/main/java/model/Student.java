package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String group;

    public Student() {
    }

    public Student(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
