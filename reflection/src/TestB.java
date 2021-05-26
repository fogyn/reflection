public class TestB {
    private String nameTest;
    private int ageTest;

    private String nameC;
    private int ageC;


    public TestB() {
    }

    public TestB(String nameTest, int ageTest) {
        this.nameTest = nameTest;
        this.ageTest = ageTest;
    }

    public TestB(String nameTest, int ageTest, String nameC, int ageC) {
        this.nameTest = nameTest;
        this.ageTest = ageTest;
        this.nameC = nameC;
        this.ageC = ageC;
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

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public int getAgeC() {
        return ageC;
    }

    public void setAgeC(int ageC) {
        this.ageC = ageC;
    }
}
