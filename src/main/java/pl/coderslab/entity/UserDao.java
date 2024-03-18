package pl.coderslab.entity;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO workshop2.users(email, username, password) VALUES (?,?,?);";
    private static final String READ_USER_QUERY =
            """
                    select id, email, username, password
                    from workshop2.users
                    where id = (?);""";

    private static final String UPDATE_USER_QUERY =
            """
                    UPDATE workshop2.users
                    set workshop2.users.email = (?), workshop2.users.username = (?), workshop2.users.password = (?)
                    where id = (?);
                    """;
    private static final String DELETE_USER_QUERY =
            """
                    DELETE workshop2.users
                    FROM workshop2.users
                    where id = (?);
                    """;
    private static final String FIND_ALL_USER_QUERY =
            """
                    SELECT *
                    FROM workshop2.users;
                    """;
}
