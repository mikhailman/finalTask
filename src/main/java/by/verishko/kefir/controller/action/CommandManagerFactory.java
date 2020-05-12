package by.verishko.kefir.controller.action;

import by.verishko.kefir.service.ServiceFactory;

public class CommandManagerFactory {

    private CommandManagerFactory() {
    }

    public static CommandManager getManager(final ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
