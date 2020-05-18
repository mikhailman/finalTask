package by.verishko.kefir.controller.action;

import by.verishko.kefir.service.ServiceFactory;

/**
 * Class factory used to create Command manager classes using the provided
 * Service factory
 */
public class CommandManagerFactory {

    /**
     * Constructs Command Factory instance.
     */
    private CommandManagerFactory() {
    }

    /**
     * Returns Command Manager instance using the provided Service factory.
     *
     * @param factory the provided Service factory.
     * @return the Command Manager instance.
     */
    public static CommandManager getManager(final ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
