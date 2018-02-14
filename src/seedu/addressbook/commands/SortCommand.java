package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list in alphabetical order with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> sortedPersons = allPersons.stream()
                .sorted(Comparator.comparing(ReadOnlyPerson::getAsTextShowAll))
                .collect(Collectors.toList());
        return new CommandResult(getMessageForPersonListShownSummary(sortedPersons), sortedPersons);
    }
}
