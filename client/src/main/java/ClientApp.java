import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config2.config"
                );
        Console console = context.getBean(Console.class);
        console.run();
    }
}
