package beam;

public class StatisticHuman {
    private String workName;
    private float successExemineDuration;
    private float unExemineDuration;

    public String getWorkName() {
        return workName;
    }

    @Override
    public String toString() {
        return "StatisticHuman{" +
                "workName='" + workName + '\'' +
                ", successExemineDuration=" + successExemineDuration +
                ", unExemineDuration=" + unExemineDuration +
                '}';
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public float getSuccessExemineDuration() {
        return successExemineDuration;
    }

    public void setSuccessExemineDuration(float successExemineDuration) {
        this.successExemineDuration = successExemineDuration;
    }

    public float getUnExemineDuration() {
        return unExemineDuration;
    }

    public void setUnExemineDuration(float unExemineDuration) {
        this.unExemineDuration = unExemineDuration;
    }
}
