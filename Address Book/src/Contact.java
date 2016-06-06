/**
 * Created by student on 2016-04-23.
 */
public class Contact {
    private String name;
    private String email;
    private long phone;
    private Category category;
    private Plec plec;

    public String getName() {
        return name;
    }

    public Plec getPlec() {
        return plec;
    }

    public void setPlec(Plec plec) {
        this.plec = plec;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getOphone() {
        return phone;
    }

    public void setPhone(long ophone) {
        this.phone = ophone;
    }

    public Category getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name +  " - " + email;
    }

    public Contact(String name, long ophone, String email, Plec plec, Category category) {
        this.name = name;
        this.phone = ophone;
        this.email = email;
        this.category = category;
        this.plec = plec;
    }
}
