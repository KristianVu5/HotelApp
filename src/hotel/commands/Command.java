package hotel.commands;

/**
 * Интерфейс за командите намиращи се в Command Line Interface.
 */
public interface Command {

    /**
     * Изпълнява команда с подадени аргументи.
     * @param args аргументи от командния ред
     */
    void execute(String[] args);
}
