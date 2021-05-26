import java.util.ArrayList;

public class TestParam {
    @AnnotationParam(key = {"edew", "DCCSDC", "CADCS"})
    private String param;
    private String[] params;

    public TestParam() {
    }

    public TestParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
