package ModelClasses;

public class CreatedClass {
    //public int id;
    public String name;
    public String access_code;
    //public String date_created;
    //public int creator;

    public CreatedClass() {
    }

    public CreatedClass( String name, String access_code) {
       // this.id = id;
        this.name = name;
        this.access_code = access_code;
       // this.date_created = date_created;
        //this.creator = creator;
    }

    public String getAccess_code() {
        return access_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }
}
