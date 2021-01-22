package domain;

import annotation.Super;

@Super
public class SuperUser extends UserTest {
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "age=" + age +
                "} " + super.toString();
    }
}
