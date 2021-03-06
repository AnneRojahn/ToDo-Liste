package app.todo.model;

/**
 * Created by Tobias Rojahn on 17.04.2017.
 */

public enum StatusEnum {
    OPEN("OPE"),
    FINISHED("FIN");

    private final String persistenceString;

    StatusEnum(String persistenceString) {
        this.persistenceString = persistenceString;
    }

    public String getPersistenceString() {
        return persistenceString;
    }

    public static StatusEnum parseFromPersistenceString(String persistenceString) {
        for(StatusEnum enumValue : StatusEnum.values()) {
            if(enumValue.getPersistenceString().equals(persistenceString)) {
                return enumValue;
            }
        }
        return null;
    }
}
