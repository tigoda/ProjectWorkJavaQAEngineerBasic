package data;

public enum EducationData {
    EVENTSCALENDAR("Календарь мероприятий"),
    INTENSIEVS("Интенсивы"),
    COURSELAUNCHCALENDAR("Календарь запуска курсов");

    private String name;


    EducationData(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
