public class TestClass {
    private String name;
    private int age;

    @TestAnnotation
    private String altName;

    public TestClass() {
    }

    public TestClass(String name, int age, String altName) {
        this.name = name;
        this.age = age;
        this.altName = altName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }
}
