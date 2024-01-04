package bezro.payload;

public class CreateClientRequest {
    private String name;
    private String email;

    public CreateClientRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CreateClientRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
