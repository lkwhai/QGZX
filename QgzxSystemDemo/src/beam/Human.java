package beam;


public class Human {
    private String workName;
    private String workDate;
    private int workWeek;
    private String workLocation;
    private String workSubstance;
    private double workDuration;
    private double exemine;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExemine() {
        return exemine;
    }

    @Override
    public String toString() {
        return "Human{" +
                "workName='" + workName + '\'' +
                ", workDate='" + workDate + '\'' +
                ", workWeek=" + workWeek +
                ", workLocation='" + workLocation + '\'' +
                ", workSubstance='" + workSubstance + '\'' +
                ", workDuration=" + workDuration +
                ", exemine=" + exemine +
                '}';
    }

    public void setExemine(double exemine) {
        this.exemine = exemine;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public int getWorkWeek() {
        return workWeek;
    }

    public void setWorkWeek(int workWeek) {
        this.workWeek = workWeek;
//        return this.workWeek;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getWorkSubstance() {
        return workSubstance;
    }

    public void setWorkSubstance(String workSubstance) {
        this.workSubstance = workSubstance;
    }

    public double getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(double workDuration) {
        this.workDuration = workDuration;
//        return this.workDuration;
    }


}
