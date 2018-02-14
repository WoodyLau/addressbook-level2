package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //unsorted version is correct
        assertCommandBehavior("list", Arrays.asList(td.dan, td.candy, td.bill, td.amy));
        //sorted version is in alphabetical order
        assertCommandBehavior("sort", Arrays.asList(td.amy, td.bill, td.candy, td.dan));
    }

    /**
     * Executes the list or sort command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertCommandBehavior(String keyword, List<ReadOnlyPerson> expectedPersonList) {
        Command command;
        switch (keyword.toLowerCase()) {
            case "list":
                command = createListCommand();
                break;
            case "sort":
                command = createSortCommand();
                break;
            default:
                command = new Command();
                break;
        }
        CommandResult result = command.execute();
        ArrayList<List<? extends ReadOnlyPerson>> PersonList = new ArrayList<>();
        result.getRelevantPersons().ifPresent(PersonList::add);
        List<ReadOnlyPerson> actualPersonList = new ArrayList<>(PersonList.get(0));
        assertEquals(actualPersonList, expectedPersonList);
    }

    private SortCommand createSortCommand() {
        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
    private ListCommand createListCommand() {
        ListCommand command = new ListCommand();
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
