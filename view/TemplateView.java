package my.rpg.view;

public abstract class TemplateView {
    public abstract void display();

    protected void printBold(String str){
        System.out.println("\033[1m" + str + "\033[0m");
    }

    protected void printOption(String str){
        System.out.println(">> " + str);
    }
}
