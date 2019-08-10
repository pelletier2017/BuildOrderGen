import support.JsonUtils;

public class Main {

    public static void main(String[] args) {
        // sets up units statically, maybe better to not do it statically
        JsonUtils.initializeUnits();
    }
}
