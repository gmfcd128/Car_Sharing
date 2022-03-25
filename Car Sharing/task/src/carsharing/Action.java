package carsharing;


public class Action {
    private String label;
    private Runnable function;
    private Menu currentMenu;
    public Action(String label, Runnable function) {
        this.label = label;
        this.function = function;
    }

    public String getLabel() {
        return this.label;
    }

    public Menu execute(Menu currentMenu) {
        this.function.run();
        this.currentMenu = currentMenu;
        return this.currentMenu;
    }
}
