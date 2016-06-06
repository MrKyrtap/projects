import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-04-23.
 */
public class ContactModel extends AbstractListModel<Contact> {
    private List<Contact> contactList = new ArrayList<>();

    public ContactModel(List<Contact> contactList) {
        contactList.add(new Contact("Krzysztof", 123, "ala@op.pl", Plec.FEMALE, Category.FAMILY));
        contactList.add(new Contact("Mariusz", 123, "ala@op.pl", Plec.FEMALE, Category.FAMILY));
        contactList.add(new Contact("Andrzej", 123, "ala@op.pl", Plec.FEMALE, Category.JOB));
    }

    public ContactModel() {

    }


    @Override
    public int getSize() {
        return contactList.size();
    }

    @Override
    public Contact getElementAt(int i) {
        return contactList.get(i);
    }

    public void add(Contact c) {
        contactList.add(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void remove(Contact c) {
        contactList.remove(c);
        fireContentsChanged(this, 0, getSize());

    }

}
