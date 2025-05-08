package Client;

public class Appliances {

    private String applianceName;
    private int applianceWatt;

    public Appliances (String applianceName, int applianceWatt ) {
        this.applianceName = applianceName;
        this.applianceWatt = applianceWatt;
    }


    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public int getApplianceWatt() {
        return applianceWatt;
    }

    public void setApplianceWatt(int applianceWatt) {
        this.applianceWatt = applianceWatt;
    }





}
