package yhc.java.design.pattern.factory;

public class JapaneseFoodFactory implements Factory {
    @Override
    public String create() {
        return "garbage";
    }

    @Override
    public String create(String prouducer) {
        return "nuclear waste";
    }
}
