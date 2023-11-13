package lab4.task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Person implements Externalizable {
    private String name;
    private LocalDate birth;
    private transient long age;
    private transient String address;

    public Person(String name, LocalDate birth, String address) {
        this.setName(name);
        this.setBirth(birth);
        this.setAddress(address);
    }
    public Person() {
        this.setName("");
        this.setBirth(LocalDate.now());
        this.setAddress("");
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
        out.writeObject(address);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((String) in.readObject());
        this.setBirth((LocalDate) in.readObject());
        this.setAddress((String) in.readObject());
    }

    @Override
    public String toString() {
        return "Person {" +
            "name='" + getName() + '\'' +
            ", birth=" + getBirth() +
            ", age=" + getAge() +
            ", address='" + getAddress() + '\'' +
            '}';

    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }
}
