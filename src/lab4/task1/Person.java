package lab4.task1;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Person implements Externalizable {
    private String name;
    private LocalDate birth;
    private transient long age;

    public Person(String name, LocalDate birth) {
        this.setName(name);
        this.setBirth(birth);
    }
    public Person() {
        this.setName("");
        this.setBirth(LocalDate.now());
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    private void setBirth(LocalDate birth) {
        this.birth = birth;
        this.setAge(ChronoUnit.YEARS.between(birth, LocalDate.now()));
    }

    public long getAge() {
        return age;
    }

    private void setAge(long age) {
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(birth);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((String) in.readObject());
        this.setBirth((LocalDate) in.readObject());
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getBirth().toString() + ", " + getAge() + "}";
    }
}
