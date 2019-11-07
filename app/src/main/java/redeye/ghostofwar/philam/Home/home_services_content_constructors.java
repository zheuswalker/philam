package redeye.ghostofwar.philam.Home;
public class home_services_content_constructors {

    private String pso_service_name;
    private String pso_service_desc;
    private String pst_service_servicename;

    public home_services_content_constructors(String pso_service_name, String pso_service_desc ,
                                              String imotion) {
        this.pso_service_name = pso_service_name;
        this.pso_service_desc = pso_service_desc;
        this.pst_service_servicename  = pst_service_servicename;
    }

    public String pst_service_servicename() {
        return pst_service_servicename;
    }
    public String pso_service_desc() {
        return pso_service_desc;
    }
    public String pso_service_name() {return pso_service_name;}

}
