public class TestA extends ParentC{
    private String nameTest;
    private int ageTest;
    //
    private String aTest;

    public TestA() {
    }

    public TestA(String nameTest, int ageTest) {
        this.nameTest = nameTest;
        this.ageTest = ageTest;
    }

    public TestA(String nameC, int ageC, String nameTest, int ageTest) {
        super(nameC, ageC);
        this.nameTest = nameTest;
        this.ageTest = ageTest;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public int getAgeTest() {
        return ageTest;
    }

    public void setAgeTest(int ageTest) {
        this.ageTest = ageTest;
    }
}
