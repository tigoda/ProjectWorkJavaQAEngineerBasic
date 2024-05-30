package data.menu;

public enum CoursesMenuData {

    PROGRAMMING("Программирование"),
    SECURITY("Безопасность"),
    TESTING("Тестирование");

    private final String name;

    CoursesMenuData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
