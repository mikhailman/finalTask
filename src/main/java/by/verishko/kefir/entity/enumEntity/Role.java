package by.verishko.kefir.entity.enumEntity;

public enum Role {

    ADMINISTRATOR("Administrator"),
    USER("User");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdRole() {
        return ordinal();
    }

    public static Role getByIdRole(final Integer id) {
//        return Role.values()[id];
        return (id == 1) ? ADMINISTRATOR : USER;
    }
}
