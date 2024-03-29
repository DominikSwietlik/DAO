package pl.coderslab.entity;

public class MainDao {
    
    public static void addMainDao(String username, String email, String password)
    {

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        userDao.create(user);
    }
    public static void readMainDao(int id)
    {
        UserDao userDao = new UserDao();
        User read = userDao.read(id);
        System.out.println(read);

    }
    public static void updateMainDao(String username, String email, String password, int id)
    {

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(id);
        UserDao userDao = new UserDao();
        userDao.update(user);
    }
    public static void deleteMainDao(int id)
    {
        UserDao userDao = new UserDao();
        userDao.delete(id);
    }
    public static void findAll()
    {
        UserDao userDao = new UserDao();
        userDao.findAll();
    }
    public static void main(String[] args) {

        //addMainDao("nowy2", "c@gg.com", "czekaj");
        findAll();
        //readMainDao(11);
    }
}
