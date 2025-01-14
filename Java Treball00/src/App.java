import org.json.simple.JSONObject;
public class App {
    public static void main(String[] args) throws Exception {
        JSONObject obj = new JSONObject();
        System.out.println("Hello, World!");
        obj.put("name","ejemplo");
        obj.put("birth","sep-1st");
        System.out.println(obj);
    }
}
